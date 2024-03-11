package com.myapps.weathernow.domain.weather

import java.time.LocalDateTime

data class WeatherModel(
    val time:LocalDateTime,
    val temperatureCelsius:Double,
    val weatherType: WeatherType,
    val humidity:Double,
    val windSpeed:Double,
    val pressure:Double
)

