package com.myapps.weathernow.data.repositories

import com.myapps.weathernow.core.mappers.toDomain
import com.myapps.weathernow.core.mappers.toDto
import com.myapps.weathernow.core.modules.qualifiers.DispatcherIO
import com.myapps.weathernow.data.local.source.WeatherLocalSource
import com.myapps.weathernow.data.remote.service.weather.WeatherForecastService
import com.myapps.weathernow.domain.WeatherRepository
import com.myapps.weathernow.domain.weather.WeatherGeographicalDataModel
import com.myapps.weathernow.domain.weather.WeatherInfoModel
import com.myapps.weathernow.utils.DataStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherForecastService: WeatherForecastService,
    private val weatherLocalSource: WeatherLocalSource,
    @DispatcherIO
    private val dispatcher: CoroutineDispatcher
): WeatherRepository {

    override fun fetchWeatherData(
        latitude: Double,
        longitude: Double,
        timezone:String?
    ): Flow<DataStatus<WeatherInfoModel>> = flow{

        val result = weatherForecastService.getWeatherData(latitude, longitude)

        if (result.isSuccessful) {
            emit(DataStatus.Success(result.body()?.toDomain()))
        } else {
            emit(DataStatus.Error(result.message()))
        }

    }.flowOn(dispatcher).catch {
        emit(DataStatus.Error(it.message))
    }

    override suspend fun upsertWeatherGeographicalData(weatherGeographicalDataModel: WeatherGeographicalDataModel) {
        weatherLocalSource.upsertWeatherGeographicalData(weatherGeographicalDataModel.toDto())
    }

    override suspend fun deleteWeatherGeographicalData(id: Int) {
        weatherLocalSource.deleteWeatherGeographicalData(id)
    }

    override suspend fun getWeatherGeographicalData(id: Int): WeatherGeographicalDataModel? =
        weatherLocalSource.getWeatherGeographicalData(id)?.toDomain()

}