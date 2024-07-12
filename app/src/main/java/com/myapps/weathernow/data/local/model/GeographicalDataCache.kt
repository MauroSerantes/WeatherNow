package com.myapps.weathernow.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GeographicalDataCache")
data class GeographicalDataCache(
    @PrimaryKey(autoGenerate = false)
    val id:Long,
    val name:String,
    val latitude:Double,
    val longitude:Double,
    val countryCode:String?,
    val country:String?,
    val timezone:String?,
    val featureCode:String,
    val admin1:String?,
    val admin2:String?,
    val admin3:String?,
    val admin4:String?
)
