package com.myapps.weathernow.domain

import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.utils.DataStatus
import kotlinx.coroutines.flow.Flow

interface GeocodingRepository {
    fun fetchGeographicalInfo(
        searchPlace:String
    ): Flow<DataStatus<List<GeographicalDataModel>>>

    suspend fun insertGeographicalData(geographicalDataModel: GeographicalDataModel)

    suspend fun deleteGeographicalData(id:Long)

    suspend fun deleteAllGeographicalData()

    fun getGeographicalData(offset: Int = 0,limit: Int = 10):Flow<List<GeographicalDataModel>>

    suspend fun thereIsNextPage(offset: Int = 10):Boolean

    suspend fun insertGeographicalDataCache(geographicalData: GeographicalDataModel)

    suspend fun deleteGeographicalDataCache(id:Long)

    suspend fun deleteAllGeographicalDataCache()

    fun getGeographicalDataCache(): Flow<List<GeographicalDataModel>>
}

