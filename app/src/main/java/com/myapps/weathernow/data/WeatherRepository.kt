package com.myapps.weathernow.data

import com.myapps.weathernow.core.mappers.toDomain
import com.myapps.weathernow.domain.Repository
import com.myapps.weathernow.domain.weather.WeatherInfo
import com.myapps.weathernow.utils.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val weatherServiceApi:WeatherForecastService
): Repository {

    override fun fetchWeatherData(
        latitude: Double,
        longitude: Double
    ): Flow<DataStatus<WeatherInfo>> = flow{

        val result = weatherServiceApi.getWeatherData(latitude, longitude)

        if (result.isSuccessful) {
            emit(DataStatus.Success(result.body()?.toDomain()))
        } else {
            emit(DataStatus.Error(result.message()))
        }

    }.flowOn(Dispatchers.IO).catch {
        emit(DataStatus.Error(it.message))
    }
    
}