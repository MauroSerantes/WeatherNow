package com.myapps.weathernow.core.mappers

import com.myapps.weathernow.data.responses.WeatherResponse
import com.myapps.weathernow.domain.weather.WeatherDailyModel
import com.myapps.weathernow.domain.weather.WeatherInfo
import com.myapps.weathernow.domain.weather.WeatherModel
import com.myapps.weathernow.domain.weather.getWeatherTypeBaseOnWMO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private class IndexedWeatherDataByDays(
    val index:Int,
    val weatherData: WeatherModel
)

private class Date(
    val day:Int,
    val month:Int,
    val year:Int
)

private fun stringToDateConversion(stringFormat:String): Date {
    val dateParts = stringFormat.split("-")
    return Date(dateParts[2].toInt(),dateParts[1].toInt(),dateParts[0].toInt())
}


fun WeatherResponse.toWeatherDataMap():Map<Int,List<WeatherModel>>{
    return hourlyData.time.mapIndexed{
        index, time ->

        val temperature = hourlyData.temperatures[index]
        val weatherCode = hourlyData.weatherCodes[index]
        val humidity = hourlyData.himidities[index]
        val windSpeed = hourlyData.windSpeeds[index]
        val pressure = hourlyData.pressures[index]
        val dayOrNight:Boolean = (hourlyData.dayOrNight[index] == 1)

        IndexedWeatherDataByDays(
            index = index,
            weatherData = WeatherModel(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                temperatureCelsius = temperature,
                weatherType = getWeatherTypeBaseOnWMO(weatherCode,dayOrNight),
                humidity = humidity,
                windSpeed = windSpeed,
                pressure = pressure
            )
        )

    }.groupBy {
        it.index/24
    }.mapValues {
        it.value.map { data -> data.weatherData }
    }
}

fun WeatherResponse.toWeatherDailyDataList():List<WeatherDailyModel>{
    return dailyData.time.mapIndexed{
        index , time ->
        val maxTemperature = dailyData.maxTemperature[index]
        val minTemperature = dailyData.minTemperature[index]
        val weatherCode  = dailyData.weatherCode[index]
        val date = stringToDateConversion(time)

        WeatherDailyModel(
            time = LocalDateTime.of(date.year,date.month,date.day,1,1),
            maxTemperature = maxTemperature,
            minTemperature = minTemperature,
            weatherType = getWeatherTypeBaseOnWMO(weatherCode,true)
        )
    }
}

fun WeatherResponse.toDomain(): WeatherInfo {
    val weatherPerDays = this.toWeatherDataMap()
    val currentTime = LocalDateTime.now()
    val weatherNow = if(currentTime.hour == 23 && currentTime.minute >=50){
        weatherPerDays[1]?.get(0)
    }
    else{
        weatherPerDays[0]?.find {
            (it.time.hour == currentTime.hour && currentTime.minute < 30) ||
                    (it.time.hour  == currentTime.hour + 1)
        }
    }
    val weatherDailyData = this.toWeatherDailyDataList()

    return WeatherInfo(
        latitude = this.latitude,
        longitude = this.longitude,
        weatherPerDay = weatherPerDays,
        weatherNow = weatherNow,
        weatherDaily = weatherDailyData
    )
}
