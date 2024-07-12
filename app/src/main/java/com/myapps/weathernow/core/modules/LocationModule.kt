package com.myapps.weathernow.core.modules

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.myapps.weathernow.data.remote.location.LocationFinder
import com.myapps.weathernow.data.remote.location.service.LocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {
    @Provides
    @Singleton
    fun providesFusedLocationTracker(app:Application):FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun providesLocationTracker(
        locationClient:FusedLocationProviderClient,
        app: Application
    ): LocationTracker = LocationFinder(locationClient,app)
}