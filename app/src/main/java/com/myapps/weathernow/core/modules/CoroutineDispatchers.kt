package com.myapps.weathernow.core.modules

import com.myapps.weathernow.core.modules.qualifiers.DispatcherDefault
import com.myapps.weathernow.core.modules.qualifiers.DispatcherIO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchers {
    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @DispatcherDefault
    fun providesDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default
}