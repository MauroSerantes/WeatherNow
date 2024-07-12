package com.myapps.weathernow.core.modules

import android.app.Application
import androidx.room.Room
import com.myapps.weathernow.data.local.service.GeographicalDataCacheDao
import com.myapps.weathernow.data.local.service.GeographicalDataDao
import com.myapps.weathernow.data.local.model.GeographicalDataDb
import com.myapps.weathernow.data.local.service.WeatherGeographicalDataDao
import com.myapps.weathernow.data.local.source.GeocodingLocalSource
import com.myapps.weathernow.data.local.source.GeocodingLocalSourceImpl
import com.myapps.weathernow.data.local.source.WeatherLocalSource
import com.myapps.weathernow.data.local.source.WeatherLocalSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun providesDatabase(app: Application): GeographicalDataDb =
        Room.databaseBuilder(app, GeographicalDataDb::class.java, "GeocodeData")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesGeographicalDataDao(geographicalDataDb: GeographicalDataDb): GeographicalDataDao =
        geographicalDataDb.geographicalDataDao()

    @Provides
    @Singleton
    fun providesGeographicalDataCacheDao(geographicalDataDb: GeographicalDataDb): GeographicalDataCacheDao =
        geographicalDataDb.geographicalDataCacheDao()

    @Provides
    @Singleton
    fun providesWeatherGeographicalDataDao(geographicalDataDb: GeographicalDataDb): WeatherGeographicalDataDao =
        geographicalDataDb.weatherGeographicalDataDao()

    @Provides
    @Singleton
    fun providesGeocodingLocalSource(
        geographicalDataDao: GeographicalDataDao,
        geographicalDataCacheDao: GeographicalDataCacheDao
    ): GeocodingLocalSource =
        GeocodingLocalSourceImpl(
            geographicalDataDao,
            geographicalDataCacheDao
        )

    @Provides
    @Singleton
    fun providesWeatherLocalSource(
        weatherGeographicalDataDao: WeatherGeographicalDataDao,
    ): WeatherLocalSource =
        WeatherLocalSourceImpl(
            weatherGeographicalDataDao
        )
}
