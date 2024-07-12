package com.myapps.weathernow.utils.service


import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.myapps.weathernow.domain.WeatherRepository
import com.myapps.weathernow.utils.Status
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit


@HiltWorker
class WeatherNotificationWork @AssistedInject constructor(
    private val weatherRepository: WeatherRepository,
    private val weatherNotification: WeatherNotification,
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    companion object {
        private const val PERIODIC_WORKER_TAG = "PeriodicAppConfigWorker"
        private const val PERIODIC_WORKER_NAME = "PushNotifications"
        private const val INITIAL_DELAY_IN_MIN = 0L
        private const val REPEAT_INTERVAL_IN_HOURS = 1L

        fun startPeriodicWork(appContext: Context?) {
            cancelCurrentPeriodicWork(appContext)
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<WeatherNotificationWork>(
                repeatInterval = REPEAT_INTERVAL_IN_HOURS,
                repeatIntervalTimeUnit = TimeUnit.HOURS
            )
                .setInitialDelay(INITIAL_DELAY_IN_MIN, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .addTag(PERIODIC_WORKER_TAG)
                .build()
            appContext?.let {
                WorkManager.getInstance(it)
                    .enqueueUniquePeriodicWork(
                        PERIODIC_WORKER_NAME,
                        ExistingPeriodicWorkPolicy.KEEP,
                        workRequest
                    )
            }
        }

        private fun isWorkScheduled(ctx: Context?): Boolean {
            ctx?.let { appContext ->
                val instance = WorkManager.getInstance(appContext)
                val statuses = instance.getWorkInfosByTag(PERIODIC_WORKER_TAG)
                return try {
                    val workInfoList = statuses.get()
                    return workInfoList.any { (it.state == WorkInfo.State.RUNNING) or (it.state == WorkInfo.State.ENQUEUED) }
                } catch (e: ExecutionException) {
                    false
                } catch (e: InterruptedException) {
                    false
                }
            } ?: kotlin.run {
                return false
            }
        }

        fun cancelCurrentPeriodicWork(ctx: Context?) {
            ctx?.let { appContext ->
                if (isWorkScheduled(appContext)) {
                    WorkManager.getInstance(appContext).cancelAllWorkByTag(PERIODIC_WORKER_TAG)
                }
            }
        }

    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val data = weatherRepository.getWeatherGeographicalData()

        if (data == null) Result.failure()

        weatherRepository.fetchWeatherData(data?.latitude!!, data.longitude).collect {
            if (it.status == Status.SUCCESS) {
                val currentTime = LocalDateTime.now()
                val weatherNow = if (currentTime.hour == 23 && currentTime.minute >= 30) {
                    it.data?.weatherPerDay?.get(1)?.get(0)
                } else {
                    it.data?.weatherPerDay?.get(0)?.find { weatherHourly ->
                        (weatherHourly.time.hour == currentTime.hour && currentTime.minute < 30) ||
                                (weatherHourly.time.hour == currentTime.hour + 1)
                    }
                }
                if (weatherNow != null) {
                    weatherNotification.sendNotification(
                        weatherNotificationData =
                        WeatherNotificationData(
                            weatherNow.time,
                            weatherNow.temperatureCelsius,
                            weatherNow.weatherType,
                            weatherNow.humidity,
                            weatherNow.windSpeed,
                            weatherNow.precipitationProbability,
                            data.cityName,
                            data.countryName
                        )
                    )
                }
            }
        }
        Result.success()
    }
}