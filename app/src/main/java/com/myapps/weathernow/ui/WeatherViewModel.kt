package com.myapps.weathernow.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.weathernow.core.cache.SimpleCacheWeatherInfo
import com.myapps.weathernow.data.remote.utils.NetworkObserver
import com.myapps.weathernow.domain.GeocodingRepository
import com.myapps.weathernow.domain.WeatherRepository
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.data.remote.location.service.LocationTracker
import com.myapps.weathernow.domain.utils.Errors
import com.myapps.weathernow.domain.weather.WeatherGeographicalDataModel
import com.myapps.weathernow.domain.weather.WeatherHourlyModel
import com.myapps.weathernow.domain.weather.WeatherInfoModel
import com.myapps.weathernow.utils.Status
import com.myapps.weathernow.utils.service.WeatherNotification
import com.myapps.weathernow.utils.service.WeatherNotificationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val geocodingRepository: GeocodingRepository,
    private val location: LocationTracker,
    private val networkObserver: NetworkObserver,
) : ViewModel() {

    private val storePair = mutableStateOf(Pair(0, 10))

    private val _uiState = MutableStateFlow<WeatherState>(WeatherState.Loading)

    private val _storedPlacesState =
        MutableStateFlow<StoredGeographicalPlacesState>(StoredGeographicalPlacesState.Loading)
    val uiState: StateFlow<WeatherState> get() = _uiState
    val storedPlacesState: StateFlow<StoredGeographicalPlacesState> get() = _storedPlacesState


    private var locationWeatherJob: Job? = null
    private var storedPlacesJob: Job? = null

    init {
        fetchStoredGeographicalData()
    }

    fun fetchWeatherInformation() {
        viewModelScope.launch {
            locationWeatherJob?.cancelAndJoin()
            locationWeatherJob = launch {
                networkObserver.isConnected.collect { connected ->
                    if (connected) {
                        getWeatherByLocation()
                    } else {
                        SimpleCacheWeatherInfo.weatherInfoState.value.let {
                            if (it == null) {
                                _uiState.value = WeatherState.Error(Errors.NO_INTERNET_CONNECTION)
                            } else {
                                _uiState.value =
                                    WeatherState.Success(SimpleCacheWeatherInfo.weatherInfoState.value!!)
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun getWeatherByLocation() {
        _uiState.value = WeatherState.Loading

        location.getCurrentLocation().let { locationData ->
            when (locationData.status) {
                Status.SUCCESS -> {
                    locationData.data?.let {
                        weatherRepository.fetchWeatherData(
                            it.latitude,
                            locationData.data!!.longitude
                        ).collect { weatherInfo ->
                            if (weatherInfo.status == Status.SUCCESS) {
                                weatherInfo.data?.cityName = it.cityName
                                weatherInfo.data?.countryName = it.countryName
                                _uiState.value = WeatherState.Success(weatherInfo.data!!)
                                SimpleCacheWeatherInfo.weatherInfoState.value = weatherInfo.data
                                weatherRepository.upsertWeatherGeographicalData(
                                    WeatherGeographicalDataModel(
                                        it.latitude, it.longitude, it.cityName,
                                        it.countryName
                                    )
                                )
                            } else {
                                if (SimpleCacheWeatherInfo.weatherInfoState.value != null) {
                                    _uiState.value = WeatherState.Success(
                                        SimpleCacheWeatherInfo.weatherInfoState.value!!
                                    )
                                } else {
                                    _uiState.value = WeatherState.Error(Errors.UNKNOWN_ERROR)
                                }
                            }
                        }
                    }
                }

                Status.ERROR -> {
                    _uiState.value = WeatherState.Error(locationData.errorMessage!!)
                }
            }
        }

    }

    fun refresh() {
        fetchWeatherInformation()
    }

    private fun fetchStoredGeographicalData() {
        viewModelScope.launch {
            storedPlacesJob?.cancelAndJoin()
            storedPlacesJob = viewModelScope.launch {
                val thereIsNextPage = geocodingRepository.thereIsNextPage(
                    storePair.value.first + storePair.value.second
                )

                _storedPlacesState.value = StoredGeographicalPlacesState.Loading
                geocodingRepository.getGeographicalData(storePair.value.first, storePair.value.second)
                    .collect {
                        _storedPlacesState.value = StoredGeographicalPlacesState.Success(
                            it,
                            nextPage = if (thereIsNextPage) {
                                {
                                    nextPage()
                                }
                            } else null,
                            prevPage = if (storePair.value.first != 0) {
                                {
                                    prevPage()
                                }
                            } else null
                        )
                    }

            }
        }
    }

    private fun nextPage() {
        storePair.value = Pair(storePair.value.first + storePair.value.second, storePair.value.second)
        fetchStoredGeographicalData()
    }

    private fun prevPage() {
        storePair.value = Pair(storePair.value.first - storePair.value.second, storePair.value.second)
        fetchStoredGeographicalData()
    }

}

