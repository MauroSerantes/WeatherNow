package com.myapps.weathernow.data.repositories

import com.myapps.weathernow.core.mappers.toDomain
import com.myapps.weathernow.core.mappers.toDto
import com.myapps.weathernow.core.modules.qualifiers.DispatcherIO
import com.myapps.weathernow.data.local.source.GeocodingLocalSource
import com.myapps.weathernow.data.remote.source.geographical.GeocodingRemoteSource
import com.myapps.weathernow.domain.GeocodingRepository
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.utils.DataStatus
import com.myapps.weathernow.utils.Status
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GeocodingRepositoryImpl @Inject constructor(
    private val geocodingRemoteSource: GeocodingRemoteSource,
    private val geocodingLocalSource: GeocodingLocalSource,
    @DispatcherIO
    private val dispatcher: CoroutineDispatcher
) : GeocodingRepository {

    override fun fetchGeographicalInfo(searchPlace: String): Flow<DataStatus<List<GeographicalDataModel>>> =
        flow {
            val result = geocodingRemoteSource.searchLocation(searchPlace)
            if (result.status == Status.SUCCESS) {
                emit(DataStatus.Success(result.data?.map { it.toDomain() }))
            } else {
                emit(DataStatus.Error(result.errorMessage))
            }
        }
            .catch { emit(DataStatus.Error(it.message)) }
            .flowOn(dispatcher)

    override suspend fun insertGeographicalData(geographicalDataModel: GeographicalDataModel) {
        geocodingLocalSource.insertGeographicalData(geographicalDataModel.toDto())
    }

    override suspend fun deleteGeographicalData(id: Long) {
        geocodingLocalSource.deleteGeographicalData(id)
    }

    override suspend fun deleteAllGeographicalData() {
        geocodingLocalSource.deleteAllGeographicalData()
    }

    override fun getGeographicalData(offset: Int, limit: Int): Flow<List<GeographicalDataModel>>  =
        geocodingLocalSource.getGeographicalData(offset,limit).map { list->
            list.map { it.toDomain() }
        }

    override suspend fun thereIsNextPage(offset: Int): Boolean {
        return geocodingLocalSource.thereIsNextPage(offset)
    }

    override suspend fun insertGeographicalDataCache(geographicalData: GeographicalDataModel) {
        geocodingLocalSource.insertGeographicalDataCache(geographicalData.toDto())
    }

    override suspend fun deleteGeographicalDataCache(id: Long) {
        geocodingLocalSource.deleteGeographicalDataCache(id)
    }

    override suspend fun deleteAllGeographicalDataCache() {
        geocodingLocalSource.deleteAllGeographicalDataCache()
    }

    override fun getGeographicalDataCache(): Flow<List<GeographicalDataModel>>  =
        geocodingLocalSource.getGeographicalDataCache().map { list->
            list.map { it.toDomain() }
        }
}