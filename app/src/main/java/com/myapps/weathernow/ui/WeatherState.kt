package com.myapps.weathernow.ui

import com.myapps.weathernow.domain.weather.WeatherInfo

sealed class WeatherState{
    data object Refresh:WeatherState()
    data object Loading:WeatherState()
    data class  Success(val weatherInfo:WeatherInfo): WeatherState()
    data class  Error(val errorMessage:String): WeatherState()
}
