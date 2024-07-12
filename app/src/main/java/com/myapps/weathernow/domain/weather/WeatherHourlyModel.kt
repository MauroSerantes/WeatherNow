package com.myapps.weathernow.domain.weather

import java.time.LocalDateTime

data class WeatherHourlyModel(
    val time:LocalDateTime,
    val temperatureCelsius:Double,
    val weatherType: WeatherType,
    val weatherCode:Int,
    val isDay:Boolean,
    val humidity:Double,
    val windSpeed:Double,
    val visibility:Double,
    val precipitationProbability:Int
)



