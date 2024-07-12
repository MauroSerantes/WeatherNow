package com.myapps.weathernow.ui

import com.myapps.weathernow.domain.weather.WeatherInfoModel

sealed class WeatherState{
    data object Loading:WeatherState()
    data class Success(val weatherInfo:WeatherInfoModel): WeatherState()
    data class Error(val errorMessage:String): WeatherState()
}
