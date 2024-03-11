package com.myapps.weathernow.core.modules

import com.myapps.weathernow.data.WeatherRepository
import com.myapps.weathernow.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providesWeatherRepository(
        weatherRepository:WeatherRepository
    ):Repository
}