package com.myapps.weathernow.utils.service

import com.myapps.weathernow.domain.weather.WeatherType
import java.time.LocalDateTime

data class WeatherNotificationData(
    val time: LocalDateTime,
    val temperatureCelsius:Double,
    val weatherType: WeatherType,
    val humidity:Double,
    val windSpeed:Double,
    val precipitationProbability:Int,
    val cityName:String?,
    val countryName:String?
)

