package com.myapps.weathernow.core.modules

import com.myapps.weathernow.core.modules.qualifiers.DispatcherIO
import com.myapps.weathernow.data.repositories.GeocodingRepositoryImpl
import com.myapps.weathernow.data.remote.service.weather.WeatherForecastService
import com.myapps.weathernow.data.repositories.WeatherRepositoryImpl
import com.myapps.weathernow.data.local.source.GeocodingLocalSource
import com.myapps.weathernow.data.local.source.WeatherLocalSource
import com.myapps.weathernow.data.remote.source.geographical.GeocodingRemoteSource
import com.myapps.weathernow.domain.GeocodingRepository
import com.myapps.weathernow.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesWeatherRepository(
        weatherServiceApi: WeatherForecastService,
        weatherLocalSource: WeatherLocalSource,
        @DispatcherIO dispatcher: CoroutineDispatcher
    ): WeatherRepository = WeatherRepositoryImpl(weatherServiceApi,weatherLocalSource,dispatcher)

    @Provides
    @Singleton
    fun providesGeocodingRepository(
        geocodingRemoteSource: GeocodingRemoteSource,
        geocodingLocalSource: GeocodingLocalSource,
        @DispatcherIO
        dispatcher: CoroutineDispatcher
    ):GeocodingRepository = GeocodingRepositoryImpl(geocodingRemoteSource,geocodingLocalSource,dispatcher)

}