package com.myapps.weathernow.utils.service

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Build
import android.os.PerformanceHintManager
import android.widget.RemoteViews
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.myapps.weathernow.R
import com.myapps.weathernow.utils.NotificationConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WeatherNotification @Inject constructor(
   @ApplicationContext private val context: Context,
    private val notificationManagerCompat: NotificationManagerCompat
) {


    fun sendNotification(weatherNotificationData: WeatherNotificationData){
        val bitmap = BitmapFactory.decodeResource(context.resources,weatherNotificationData.weatherType.iconRes)
        createNotificationChannel()
        val notAct = NotificationCompat.Action.Builder(R.drawable.ic_wind,"helle",null).build()
        val notification = NotificationCompat.Builder(context, NotificationConstants.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.clouds)
            .setContentTitle(weatherNotificationData.weatherType.weatherDescription +"  "+weatherNotificationData.temperatureCelsius+"°")
            .setContentText(weatherNotificationData.cityName+" - "+weatherNotificationData.countryName)
            .setLargeIcon(bitmap)
            .setStyle(NotificationCompat.BigTextStyle().bigText(
                "Precipitation probability: "+ weatherNotificationData.precipitationProbability.toString() + "% \n" +
                "Humidity: "+weatherNotificationData.humidity.toString()+" % \n" +
                "Wind Speed: "+weatherNotificationData.windSpeed.toString()+" Km/h \n"

            ))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManagerCompat.notify(1,notification)
        }
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationConstants.NOTIFICATION_CHANNEL_ID,
                NotificationConstants.NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManagerCompat.createNotificationChannel(channel)
        }
    }
}