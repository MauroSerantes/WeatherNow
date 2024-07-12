package com.myapps.weathernow.core.cache

import androidx.compose.runtime.mutableStateOf
import com.myapps.weathernow.domain.weather.WeatherInfoModel

object SimpleCacheWeatherInfo {
    val weatherInfoState = mutableStateOf<WeatherInfoModel?>(value = null)
}