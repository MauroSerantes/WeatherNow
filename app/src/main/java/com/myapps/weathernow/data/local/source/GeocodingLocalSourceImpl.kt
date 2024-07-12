package com.myapps.weathernow.data.local.source

import com.myapps.weathernow.core.mappers.toDto
import com.myapps.weathernow.core.mappers.toLocal
import com.myapps.weathernow.core.mappers.toLocalCache
import com.myapps.weathernow.data.local.service.GeographicalDataCacheDao
import com.myapps.weathernow.data.local.service.GeographicalDataDao
import com.myapps.weathernow.data.model.geographical.GeographicalDataDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GeocodingLocalSourceImpl @Inject constructor(
    private val geographicalDataDao: GeographicalDataDao,
    private val geographicalDataCacheDao: GeographicalDataCacheDao
):GeocodingLocalSource {
    override suspend fun insertGeographicalData(geographicalDataDto: GeographicalDataDto) {
       geographicalDataDao.insertGeographicalData(geographicalDataDto.toLocal())
    }

    override suspend fun deleteGeographicalData(id: Long) {
        geographicalDataDao.deleteGeographicalData(id)
    }

    override suspend fun deleteAllGeographicalData() {
        geographicalDataDao.deleteAllGeographicalData()
    }

    override fun getGeographicalData(offset: Int, limit: Int): Flow<List<GeographicalDataDto>> =
        geographicalDataDao.getGeographicalData(offset,limit).map {list->
            list.map { it.toDto() }
        }

    override suspend fun thereIsNextPage(offset: Int): Boolean {
       return offset < geographicalDataDao.geographicalDataCount()
    }


    override suspend fun insertGeographicalDataCache(geographicalDataDto: GeographicalDataDto) {
        geographicalDataCacheDao.insertGeographicalDataCache(geographicalDataDto.toLocalCache())
    }

    override suspend fun deleteGeographicalDataCache(id: Long) {
       geographicalDataCacheDao.deleteGeographicalDataCache(id)
    }

    override suspend fun deleteAllGeographicalDataCache() {
        geographicalDataCacheDao.deleteAllGeographicalDataCache()
    }

    override fun getGeographicalDataCache(): Flow<List<GeographicalDataDto>> =
        geographicalDataCacheDao.getGeographicalDataCache().map { list->
            list.map { it.toDto() }
        }
}