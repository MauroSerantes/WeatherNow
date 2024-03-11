package com.myapps.weathernow.data.responses

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val latitude:Double,
    val longitude:Double,
    @SerializedName("hourly")
    val hourlyData:HourlyData,
    @SerializedName("daily")
    val dailyData:DailyData
)

data class HourlyData(
    val time:List<String>,
    @SerializedName("temperature_2m")
    val temperatures:List<Double>,
    @SerializedName("weather_code")
    val weatherCodes:List<Int>,
    @SerializedName("relative_humidity_2m")
    val himidities:List<Double>,
    @SerializedName("wind_speed_10m")
    val windSpeeds:List<Double>,
    @SerializedName("surface_pressure")
    val pressures:List<Double>,
    @SerializedName("is_day")
    val dayOrNight:List<Int>
)

data class DailyData(
    val time:List<String>,
    @SerializedName("temperature_2m_max")
    val maxTemperature:List<Double>,
    @SerializedName("temperature_2m_min")
    val minTemperature:List<Double>,
    @SerializedName("weather_code")
    val weatherCode:List<Int>
)
