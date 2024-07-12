package com.myapps.weathernow.core.mappers

import com.myapps.weathernow.data.local.model.GeographicalData
import com.myapps.weathernow.data.local.model.GeographicalDataCache
import com.myapps.weathernow.data.local.model.WeatherGeographicalData
import com.myapps.weathernow.data.local.model.constants.DatabaseConstants
import com.myapps.weathernow.data.model.geographical.GeographicalDataDto
import com.myapps.weathernow.data.model.weather.WeatherGeographicalDataDto
import com.myapps.weathernow.data.remote.model.geographical.GeographicalDataResponse
import com.myapps.weathernow.data.remote.model.weather.WeatherResponse
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.domain.weather.WeatherDailyModel
import com.myapps.weathernow.domain.weather.WeatherGeographicalDataModel
import com.myapps.weathernow.domain.weather.WeatherInfoModel
import com.myapps.weathernow.domain.weather.WeatherHourlyModel
import com.myapps.weathernow.domain.weather.getWeatherTypeBaseOnWMO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private class IndexedWeatherDataByDays(
    val index: Int,
    val weatherData: WeatherHourlyModel
)

private class Date(
    val day: Int,
    val month: Int,
    val year: Int
)

private fun stringToDateConversion(stringFormat: String): Date {
    val dateParts = stringFormat.split("-")
    return Date(dateParts[2].toInt(), dateParts[1].toInt(), dateParts[0].toInt())
}


fun WeatherResponse.toWeatherDataMap(): Map<Int, List<WeatherHourlyModel>> {
    return hourlyData.time.mapIndexed { index, time ->

        val temperature = hourlyData.temperatures[index]
        val weatherCode = hourlyData.weatherCodes[index]
        val humidity = hourlyData.humidity[index]
        val windSpeed = hourlyData.windSpeeds[index]
        val visibility = hourlyData.visibilities[index]
        val dayOrNight: Boolean = (hourlyData.dayOrNight[index] == 1)
        val precipitationProbability = hourlyData.precipitationProbability[index]

        IndexedWeatherDataByDays(
            index = index,
            weatherData = WeatherHourlyModel(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                temperatureCelsius = temperature,
                weatherType = getWeatherTypeBaseOnWMO(weatherCode, dayOrNight),
                weatherCode = weatherCode,
                isDay = dayOrNight,
                humidity = humidity,
                windSpeed = windSpeed,
                visibility = visibility,
                precipitationProbability = precipitationProbability
            )
        )

    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { data -> data.weatherData }
    }
}

fun WeatherResponse.toWeatherDailyDataList(): List<WeatherDailyModel> {
    return dailyData.time.mapIndexed { index, time ->
        val maxTemperature = dailyData.maxTemperature[index]
        val minTemperature = dailyData.minTemperature[index]
        val weatherCode = dailyData.weatherCode[index]
        val precipitationProbability = dailyData.precipitationProbability[index]
        val windSpeed = dailyData.windSpeed[index]
        val sunrise = dailyData.sunrise[index]
        val sunset = dailyData.sunset[index]
        val date = stringToDateConversion(time)

        WeatherDailyModel(
            time = LocalDateTime.of(date.year, date.month, date.day, 1, 1),
            maxTemperature = maxTemperature,
            minTemperature = minTemperature,
            weatherType = getWeatherTypeBaseOnWMO(weatherCode, true),
            weatherCode = weatherCode,
            precipitationProbability = precipitationProbability,
            windSpeed = windSpeed,
            sunrise = LocalDateTime.parse(sunrise, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            sunset = LocalDateTime.parse(sunset, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        )
    }
}

fun WeatherResponse.toDomain(): WeatherInfoModel {
    val weatherPerDays = this.toWeatherDataMap()


    val weatherDailyData = this.toWeatherDailyDataList()

    return WeatherInfoModel(
        latitude = this.latitude,
        longitude = this.longitude,
        weatherPerDay = weatherPerDays,
        weatherDaily = weatherDailyData
    )
}

fun GeographicalDataResponse.toDto(): GeographicalDataDto =
    GeographicalDataDto(
        id = this.id,
        name = this.name,
        latitude = this.latitude,
        longitude = this.longitude,
        countryCode = this.countryCode,
        country = this.country,
        timezone = this.timezone,
        featureCode = this.featureCode,
        admin1 = this.admin1,
        admin2 = this.admin2,
        admin3 = this.admin3,
        admin4 = this.admin4
    )


fun GeographicalDataModel.toDto(): GeographicalDataDto =
    GeographicalDataDto(
        this.id,
        this.name,
        this.latitude,
        this.longitude,
        this.countryCode,
        this.country,
        this.timezone,
        this.featureCode,
        this.admin1,
        this.admin2,
        this.admin3,
        this.admin4
    )


fun GeographicalData.toDto(): GeographicalDataDto =
    GeographicalDataDto(
        this.id,
        this.name,
        this.latitude,
        this.longitude,
        this.countryCode,
        this.country,
        this.timezone,
        this.featureCode,
        this.admin1,
        this.admin2,
        this.admin3,
        this.admin4
    )

fun GeographicalDataCache.toDto(): GeographicalDataDto =
    GeographicalDataDto(
        this.id,
        this.name,
        this.latitude,
        this.longitude,
        this.countryCode,
        this.country,
        this.timezone,
        this.featureCode,
        this.admin1,
        this.admin2,
        this.admin3,
        this.admin4
    )

fun GeographicalDataDto.toDomain(): GeographicalDataModel =
    GeographicalDataModel(
        this.id,
        this.name,
        this.latitude,
        this.longitude,
        this.countryCode,
        this.country,
        this.timezone,
        this.featureCode,
        this.admin1,
        this.admin2,
        this.admin3,
        this.admin4
    )

fun GeographicalDataDto.toLocal(): GeographicalData =
    GeographicalData(
        this.id,
        this.name,
        this.latitude,
        this.longitude,
        this.countryCode,
        this.country,
        this.timezone,
        this.featureCode,
        this.admin1,
        this.admin2,
        this.admin3,
        this.admin4
    )

fun GeographicalDataDto.toLocalCache(): GeographicalDataCache =
    GeographicalDataCache(
        this.id,
        this.name,
        this.latitude,
        this.longitude,
        this.countryCode,
        this.country,
        this.timezone,
        this.featureCode,
        this.admin1,
        this.admin2,
        this.admin3,
        this.admin4
    )

fun WeatherGeographicalData.toDto(): WeatherGeographicalDataDto =
    WeatherGeographicalDataDto(
        latitude,
        longitude,
        cityName,
        countryName
    )

fun WeatherGeographicalDataDto.toData(): WeatherGeographicalData =
    WeatherGeographicalData(
        id = DatabaseConstants.WEATHER_GEOGRAPHICAL_ID,
        latitude = latitude,
        longitude = longitude,
        cityName = cityName,
        countryName = countryName
    )


fun WeatherGeographicalDataModel.toDto(): WeatherGeographicalDataDto =
    WeatherGeographicalDataDto(
        latitude, longitude, cityName, countryName
    )

fun WeatherGeographicalDataDto.toDomain(): WeatherGeographicalDataModel =
    WeatherGeographicalDataModel(
        latitude, longitude, cityName, countryName
    )