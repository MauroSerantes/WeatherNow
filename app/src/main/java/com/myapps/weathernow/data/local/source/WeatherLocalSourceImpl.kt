package com.myapps.weathernow.data.local.source

import com.myapps.weathernow.core.mappers.toData
import com.myapps.weathernow.core.mappers.toDomain
import com.myapps.weathernow.core.mappers.toDto
import com.myapps.weathernow.data.local.service.WeatherGeographicalDataDao
import com.myapps.weathernow.data.model.weather.WeatherGeographicalDataDto
import com.myapps.weathernow.domain.weather.WeatherHourlyModel
import java.time.LocalDateTime
import javax.inject.Inject

class WeatherLocalSourceImpl @Inject constructor(
    private val weatherGeographicalDataDao: WeatherGeographicalDataDao
):WeatherLocalSource {
    override suspend fun upsertWeatherGeographicalData(weatherGeographicalData: WeatherGeographicalDataDto) {
        weatherGeographicalDataDao.upsertWeatherGeographicalData(weatherGeographicalData.toData())
    }

    override suspend fun deleteWeatherGeographicalData(id: Int) {
        weatherGeographicalDataDao.deleteWeatherGeographicalData(id)
    }

    override suspend fun getWeatherGeographicalData(id: Int): WeatherGeographicalDataDto?  =
        weatherGeographicalDataDao.getWeatherGeographicalData(id)?.toDto()
}