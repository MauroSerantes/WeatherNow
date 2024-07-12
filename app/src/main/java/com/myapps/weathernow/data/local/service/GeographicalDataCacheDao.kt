package com.myapps.weathernow.data.local.service

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.myapps.weathernow.data.local.model.GeographicalDataCache
import kotlinx.coroutines.flow.Flow

@Dao
interface GeographicalDataCacheDao {
    @Upsert
    suspend fun insertGeographicalDataCache(geographicalData: GeographicalDataCache)

    @Query("DELETE FROM GeographicalDataCache WHERE  id=:id")
    suspend fun deleteGeographicalDataCache(id:Long)

    @Query("DELETE FROM GeographicalDataCache")
    suspend fun deleteAllGeographicalDataCache()

    @Query("SELECT * FROM GeographicalDataCache")
    fun getGeographicalDataCache(): Flow<List<GeographicalDataCache>>
}