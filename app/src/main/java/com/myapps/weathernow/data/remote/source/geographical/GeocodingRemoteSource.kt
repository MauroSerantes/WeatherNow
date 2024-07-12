package com.myapps.weathernow.data.remote.source.geographical

import com.myapps.weathernow.data.model.geographical.GeographicalDataDto
import com.myapps.weathernow.utils.DataStatus

interface GeocodingRemoteSource {
    suspend fun searchLocation(name:String):DataStatus<List<GeographicalDataDto>>
}