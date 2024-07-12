package com.myapps.weathernow.data.remote.service.weather

import com.myapps.weathernow.data.remote.model.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {
    @GET("v1/forecast?hourly=temperature_2m,weather_code,relative_humidity_2m,wind_speed_10m,visibility,is_day,precipitation_probability" +
            "&daily=temperature_2m_max,temperature_2m_min,weather_code,precipitation_probability_max,wind_speed_10m_max,sunrise,sunset")
    suspend fun getWeatherData(
        @Query("latitude")
        latitude:Double,
        @Query("longitude")
        longitude: Double,
        @Query("timezone")
        timezone:String?="auto"
    ):Response<WeatherResponse>

}