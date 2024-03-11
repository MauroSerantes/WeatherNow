package com.myapps.weathernow.domain.weather


data class WeatherInfo(
    val latitude:Double,
    val longitude:Double,
    val weatherPerDay: Map<Int,List<WeatherModel>>,
    val weatherNow: WeatherModel?,
    val weatherDaily: List<WeatherDailyModel>,
    var cityName:String?=null
)