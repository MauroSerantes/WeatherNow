package com.myapps.weathernow.data

import com.myapps.weathernow.data.responses.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {
    @GET("v1/forecast?hourly=temperature_2m,weather_code,relative_humidity_2m,wind_speed_10m,surface_pressure,is_day" +
            "&daily=temperature_2m_max,temperature_2m_min,weather_code")
    suspend fun getWeatherData(
        @Query("latitude")
        latitude:Double,
        @Query("longitude")
        longitude: Double
    ):Response<WeatherResponse>
}