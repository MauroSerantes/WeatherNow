package com.myapps.weathernow.data.model.weather

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class WeatherGeographicalDataDto(
    val latitude: Double,
    val longitude: Double,
    val cityName: String?,
    val countryName: String?
)
