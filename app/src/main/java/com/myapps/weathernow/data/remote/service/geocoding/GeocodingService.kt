package com.myapps.weathernow.data.remote.service.geocoding

import com.myapps.weathernow.data.remote.model.geographical.GeographicalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {
    @GET("v1/search?count=30")
    suspend fun searchLocation(
        @Query("name")
        name:String
    ):Response<GeographicalResponse>
}
