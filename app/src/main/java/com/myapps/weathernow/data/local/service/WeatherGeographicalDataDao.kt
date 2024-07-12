package com.myapps.weathernow.data.local.service

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.myapps.weathernow.data.local.model.WeatherGeographicalData
import org.intellij.lang.annotations.Flow

@Dao
interface WeatherGeographicalDataDao {
    @Upsert
    suspend fun upsertWeatherGeographicalData(weatherGeographicalData: WeatherGeographicalData)

    @Query("DELETE FROM WeatherGeographicalData WHERE id=:id")
    suspend fun deleteWeatherGeographicalData(id:Int)

    @Query("SELECT * FROM WeatherGeographicalData WHERE id=:id")
   suspend fun getWeatherGeographicalData(id:Int): WeatherGeographicalData?
}