package com.myapps.weathernow.core.cache

import androidx.compose.runtime.mutableStateOf
import com.myapps.weathernow.domain.weather.WeatherInfo
import kotlinx.coroutines.flow.emptyFlow

object SimpleCacheWeatherInfo {
    val weatherInfoState = mutableStateOf<WeatherInfo?>(value = null)
}