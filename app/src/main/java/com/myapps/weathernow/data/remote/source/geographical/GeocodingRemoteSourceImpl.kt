package com.myapps.weathernow.data.remote.source.geographical

import com.myapps.weathernow.core.mappers.toDto
import com.myapps.weathernow.data.model.geographical.GeographicalDataDto
import com.myapps.weathernow.data.remote.service.geocoding.GeocodingService
import com.myapps.weathernow.utils.DataStatus
import javax.inject.Inject

class GeocodingRemoteSourceImpl @Inject constructor(
    private val geocodingService: GeocodingService
) : GeocodingRemoteSource{


    override suspend fun searchLocation(name: String): DataStatus<List<GeographicalDataDto>> {
        val result = geocodingService.searchLocation(name)

        if (result.isSuccessful) {
            return DataStatus.Success(
                result.body()?.results?.map {
                    it.toDto()
                }?: emptyList()
            )
        }
        return DataStatus.Error(result.message())
    }
}