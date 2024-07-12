package com.myapps.weathernow.data.local.source


import com.myapps.weathernow.data.model.geographical.GeographicalDataDto
import kotlinx.coroutines.flow.Flow

interface GeocodingLocalSource {
    suspend fun insertGeographicalData(geographicalDataDto: GeographicalDataDto)

    suspend fun deleteGeographicalData(id:Long)

    suspend fun deleteAllGeographicalData()

    fun getGeographicalData(offset:Int,limit:Int): Flow<List<GeographicalDataDto>>

    suspend fun thereIsNextPage(offset: Int):Boolean

    suspend fun insertGeographicalDataCache(geographicalDataDto: GeographicalDataDto)

    suspend fun deleteGeographicalDataCache(id:Long)

    suspend fun deleteAllGeographicalDataCache()

    fun getGeographicalDataCache(): Flow<List<GeographicalDataDto>>
}