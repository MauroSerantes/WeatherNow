package com.myapps.weathernow.domain

import com.myapps.weathernow.data.local.model.constants.DatabaseConstants
import com.myapps.weathernow.domain.weather.WeatherGeographicalDataModel
import com.myapps.weathernow.domain.weather.WeatherInfoModel
import com.myapps.weathernow.utils.DataStatus
import kotlinx.coroutines.flow.Flow

interface WeatherRepository{
    fun fetchWeatherData(
        latitude:Double,
        longitude:Double,
        timezone:String?=null
    ): Flow<DataStatus<WeatherInfoModel>>

    suspend fun upsertWeatherGeographicalData(weatherGeographicalDataModel: WeatherGeographicalDataModel)

    suspend fun deleteWeatherGeographicalData(id:Int = DatabaseConstants.WEATHER_GEOGRAPHICAL_ID)

    suspend fun getWeatherGeographicalData(id:Int = DatabaseConstants.WEATHER_GEOGRAPHICAL_ID):WeatherGeographicalDataModel?

}