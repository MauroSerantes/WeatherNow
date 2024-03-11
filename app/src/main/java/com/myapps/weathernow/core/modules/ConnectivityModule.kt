package com.myapps.weathernow.core.modules

import com.myapps.weathernow.data.utils.ConnectivityObserver
import com.myapps.weathernow.data.utils.ConnectivityObserverImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityModule {
    @Binds
    @Singleton
    abstract fun bindLocationTracker(connectivityObserverImpl: ConnectivityObserverImpl): ConnectivityObserver
}