package com.myapps.weathernow.ui.placescreen


import com.myapps.weathernow.domain.weather.WeatherInfoModel

sealed class OtherPlacesWeatherState {
    data object Loading:OtherPlacesWeatherState()
    data class Success(val weatherInfoModel: WeatherInfoModel):OtherPlacesWeatherState()
    data class Error(val message:String):OtherPlacesWeatherState()
}