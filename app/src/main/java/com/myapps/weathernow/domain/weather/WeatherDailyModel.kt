package com.myapps.weathernow.domain.weather

import java.time.LocalDateTime


data class WeatherDailyModel(
    val time:LocalDateTime,
    val minTemperature:Double,
    val maxTemperature:Double,
    val weatherType: WeatherType
)
