package com.myapps.weathernow.data.local.source

import com.myapps.weathernow.data.model.weather.WeatherGeographicalDataDto
import com.myapps.weathernow.domain.weather.WeatherHourlyModel
import java.time.LocalDateTime

interface WeatherLocalSource {
    suspend fun upsertWeatherGeographicalData(weatherGeographicalData: WeatherGeographicalDataDto)

    suspend fun deleteWeatherGeographicalData(id:Int)

    suspend fun getWeatherGeographicalData(id:Int): WeatherGeographicalDataDto?
}