package com.myapps.weathernow.core.modules

import android.app.Application
import com.myapps.weathernow.data.utils.NetworkObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkConnectionModule {
    @Provides
    @Singleton
    fun providesNetworkObserver(app: Application): NetworkObserver {
        return NetworkObserver(app)
    }
}