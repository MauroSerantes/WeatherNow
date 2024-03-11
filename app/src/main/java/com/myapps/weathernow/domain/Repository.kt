package com.myapps.weathernow.domain

import com.myapps.weathernow.domain.weather.WeatherInfo
import com.myapps.weathernow.utils.DataStatus
import kotlinx.coroutines.flow.Flow

interface Repository{
    fun fetchWeatherData(
        latitude:Double,
        longitude:Double
    ): Flow<DataStatus<WeatherInfo>>
}