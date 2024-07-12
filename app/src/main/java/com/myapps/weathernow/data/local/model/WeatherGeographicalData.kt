package com.myapps.weathernow.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherGeographicalData")
data class WeatherGeographicalData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
    @ColumnInfo(name = "city_name")
    val cityName: String?,
    @ColumnInfo(name = "country_name")
    val countryName: String?
)
