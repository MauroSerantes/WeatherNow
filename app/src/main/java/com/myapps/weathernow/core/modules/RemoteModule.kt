package com.myapps.weathernow.core.modules

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.myapps.weathernow.data.WeatherForecastService
import com.myapps.weathernow.data.utils.NetworkObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesWeatherForecastService():WeatherForecastService{
        return Retrofit
            .Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherForecastService::class.java)
    }

    @Provides
    @Singleton
    fun providesFusedLocationTracker(app:Application):FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun providesNetworkObserver(app:Application): NetworkObserver {
        return NetworkObserver(app)
    }
}