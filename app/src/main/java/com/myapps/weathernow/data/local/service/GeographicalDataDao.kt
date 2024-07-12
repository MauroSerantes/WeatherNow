package com.myapps.weathernow.data.local.service

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.myapps.weathernow.data.local.model.GeographicalData
import kotlinx.coroutines.flow.Flow

@Dao
interface GeographicalDataDao {
    @Upsert
    suspend fun insertGeographicalData(geographicalData: GeographicalData)

    @Query("DELETE FROM GeographicalData WHERE  id=:id")
    suspend fun deleteGeographicalData(id:Long)

    @Query("DELETE FROM GeographicalData")
    suspend fun deleteAllGeographicalData()

    @Query("SELECT * FROM GeographicalData LIMIT:limit OFFSET:offset")
    fun getGeographicalData(offset:Int,limit:Int): Flow<List<GeographicalData>>

    @Query("SELECT COUNT (*) FROM GeographicalData")
    suspend fun geographicalDataCount():Int
}