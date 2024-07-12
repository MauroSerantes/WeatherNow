package com.myapps.weathernow.domain.weather


data class WeatherInfoModel(
    val latitude:Double,
    val longitude:Double,
    val weatherPerDay: Map<Int,List<WeatherHourlyModel>>,
    val weatherDaily: List<WeatherDailyModel>,
    var cityName:String?=null,
    var countryName:String?=null
)