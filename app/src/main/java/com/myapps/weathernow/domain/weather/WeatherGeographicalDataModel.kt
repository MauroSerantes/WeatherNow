package com.myapps.weathernow.domain.weather


data class WeatherGeographicalDataModel(
    val latitude: Double,
    val longitude: Double,
    val cityName: String?,
    val countryName: String?
)