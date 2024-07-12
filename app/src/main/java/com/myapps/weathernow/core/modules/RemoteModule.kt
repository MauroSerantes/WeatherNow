package com.myapps.weathernow.core.modules

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myapps.weathernow.core.modules.qualifiers.GeocodingUrl
import com.myapps.weathernow.core.modules.qualifiers.WeatherUrl
import com.myapps.weathernow.core.utils.ServicesProperties.GEOCODING_BASE_URL
import com.myapps.weathernow.core.utils.ServicesProperties.WEATHER_BASE_URL
import com.myapps.weathernow.core.utils.ServicesProperties.NETWORK_TIMEOUT
import com.myapps.weathernow.data.remote.service.geocoding.GeocodingService
import com.myapps.weathernow.data.remote.service.weather.WeatherForecastService
import com.myapps.weathernow.data.remote.source.geographical.GeocodingRemoteSource
import com.myapps.weathernow.data.remote.source.geographical.GeocodingRemoteSourceImpl
import com.myapps.weathernow.data.remote.utils.NetworkObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {


    @Provides
    @WeatherUrl
    fun providesWeatherBaseUrl(): String = WEATHER_BASE_URL

    @Provides
    @GeocodingUrl
    fun providesGeocodingBaseUrl(): String = GEOCODING_BASE_URL

    @Provides
    @Singleton
    fun providesNetworkTimeout(): Long = NETWORK_TIMEOUT

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun providesBodyInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun providesClient(
        time: Long,
        body: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(body)
        .connectTimeout(time, TimeUnit.SECONDS)
        .readTimeout(time, TimeUnit.SECONDS)
        .writeTimeout(time, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()


    @Provides
    @Singleton
    fun providesWeatherForecastService(
        @WeatherUrl
        baseUrl: String,
        client: OkHttpClient,
        gson: Gson
    ): WeatherForecastService {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WeatherForecastService::class.java)
    }

    @Provides
    @Singleton
    fun providesGeocodingService(
        @GeocodingUrl
        baseUrl: String,
        client: OkHttpClient,
        gson: Gson
    ): GeocodingService {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GeocodingService::class.java)
    }


    @Provides
    @Singleton
    fun providesNetworkObserver(app: Application): NetworkObserver {
        return NetworkObserver(app)
    }


    @Provides
    @Singleton
    fun providesGeocodingRemoteSource(geocodingService: GeocodingService): GeocodingRemoteSource =
        GeocodingRemoteSourceImpl(geocodingService)

}