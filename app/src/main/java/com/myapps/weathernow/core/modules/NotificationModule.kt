package com.myapps.weathernow.core.modules

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.myapps.weathernow.R
import com.myapps.weathernow.utils.NotificationConstants
import com.myapps.weathernow.utils.service.WeatherNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {
    @Singleton
    @Provides
    fun providesNotificationManager(
        @ApplicationContext context: Context
    ):NotificationManagerCompat{
         return NotificationManagerCompat.from(context)
    }

    @Singleton
    @Provides
    fun providesWeatherNotification(
        @ApplicationContext context: Context,
        notificationManagerCompat: NotificationManagerCompat
    ):WeatherNotification =
        WeatherNotification(context,notificationManagerCompat)
}