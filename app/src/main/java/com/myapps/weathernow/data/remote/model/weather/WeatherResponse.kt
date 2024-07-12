package com.myapps.weathernow.data.remote.model.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val latitude:Double,
    val longitude:Double,
    @SerializedName("hourly")
    val hourlyData: HourlyData,
    @SerializedName("daily")
    val dailyData: DailyData
)

data class HourlyData(
    val time:List<String>,
    @SerializedName("temperature_2m")
    val temperatures:List<Double>,
    @SerializedName("weather_code")
    val weatherCodes:List<Int>,
    @SerializedName("relative_humidity_2m")
    val humidity:List<Double>,
    @SerializedName("wind_speed_10m")
    val windSpeeds:List<Double>,
    @SerializedName("visibility")
    val visibilities:List<Double>,
    @SerializedName("is_day")
    val dayOrNight:List<Int>,
    @SerializedName("precipitation_probability")
    val precipitationProbability:List<Int>
)

data class DailyData(
    val time:List<String>,
    @SerializedName("temperature_2m_max")
    val maxTemperature:List<Double>,
    @SerializedName("temperature_2m_min")
    val minTemperature:List<Double>,
    @SerializedName("weather_code")
    val weatherCode:List<Int>,
    @SerializedName("precipitation_probability_max")
    val precipitationProbability:List<Int>,
    @SerializedName("wind_speed_10m_max")
    val windSpeed:List<Double>,
    @SerializedName("sunrise")
    val sunrise:List<String>,
    @SerializedName("sunset")
    val sunset:List<String>
)
