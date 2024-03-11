package com.myapps.weathernow.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.weathernow.core.cache.SimpleCacheWeatherInfo
import com.myapps.weathernow.data.WeatherRepository
import com.myapps.weathernow.data.utils.NetworkObserver
import com.myapps.weathernow.domain.location.LocationTracker
import com.myapps.weathernow.domain.utils.Errors
import com.myapps.weathernow.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val location:LocationTracker,
    private val networkObserver: NetworkObserver
):ViewModel(){
    private val _uiState = MutableStateFlow<WeatherState>(WeatherState.Loading)

    val uiState:StateFlow<WeatherState> get() = _uiState


    init {
        onRefreshState()
    }

    fun fetchWeatherInformation(){
        viewModelScope.launch {
            networkObserver.isConnected.collect{connected->
                if(connected){
                    getWeatherByLocation()
                }
                else {
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

    private fun getWeatherByLocation(){
        _uiState.value = WeatherState.Loading

        viewModelScope.launch{
            location.getCurrentLocation().let { locationData ->
                when(locationData.status){
                    Status.SUCCESS->{
                        locationData.data?.let {
                            repository.fetchWeatherData(it.latitude,locationData.data.longitude).collect{ weatherInfo ->
                                if(weatherInfo.status == Status.SUCCESS){
                                    _uiState.value = WeatherState.Success(weatherInfo.data!!)
                                    SimpleCacheWeatherInfo.weatherInfoState.value = weatherInfo.data
                                } else{
                                    if(SimpleCacheWeatherInfo.weatherInfoState.value!=null){
                                        _uiState.value = WeatherState.Success(
                                            SimpleCacheWeatherInfo.weatherInfoState.value!!)
                                    } else{
                                        _uiState.value = WeatherState.Error(weatherInfo.errorMessage!!)
                                    }
                                }
                            }
                        }
                    }
                    Status.ERROR->{
                        _uiState.value = WeatherState.Error(locationData.errorMessage!!)
                    }
                }
            }
        }
    }

    fun refresh(){
        _uiState.value = WeatherState.Refresh
    }

    private fun onRefreshState(){
       viewModelScope.launch {
           _uiState.collect{ weatherState->
               if(weatherState == WeatherState.Refresh){
                   fetchWeatherInformation()
               }
           }
       }
    }
}

