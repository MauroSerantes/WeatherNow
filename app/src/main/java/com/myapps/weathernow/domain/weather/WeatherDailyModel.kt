package com.myapps.weathernow.domain.weather

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


data class WeatherDailyModel(
    val time:LocalDateTime,
    val minTemperature:Double,
    val maxTemperature:Double,
    val weatherType: WeatherType,
    val weatherCode:Int,
    val precipitationProbability:Int,
    val windSpeed: Double,
    val sunrise:LocalDateTime,
    val sunset:LocalDateTime
)
