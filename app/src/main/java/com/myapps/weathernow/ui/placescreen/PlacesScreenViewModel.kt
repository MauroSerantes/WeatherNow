package com.myapps.weathernow.ui.placescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.weathernow.domain.GeocodingRepository
import com.myapps.weathernow.domain.WeatherRepository
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.ui.placescreen.cache.GeographicalModelCache
import com.myapps.weathernow.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val geocodingRepository: GeocodingRepository
):ViewModel() {

    private val _otherPlacesWeatherState = MutableStateFlow<OtherPlacesWeatherState>(OtherPlacesWeatherState.Loading)
    val otherPlacesWeatherState:StateFlow<OtherPlacesWeatherState> get() = _otherPlacesWeatherState

    init {
        val data = GeographicalModelCache.geographicalModelCache.value
        if(data!=null){
            fetchWeatherData(data.latitude,data.longitude,data.timezone)
        }
        else{
            _otherPlacesWeatherState.value = OtherPlacesWeatherState.Error("something wrong happened")
        }
    }

    private fun fetchWeatherData(latitude:Double,longitude:Double,timeZone:String?){
        viewModelScope.launch {
            weatherRepository.fetchWeatherData(latitude,longitude,timeZone).collect{ status ->
                if(status.status == Status.SUCCESS){
                    _otherPlacesWeatherState.value = OtherPlacesWeatherState.Success(status.data!!)
                }
                else{
                    _otherPlacesWeatherState.value = OtherPlacesWeatherState.Error(status.errorMessage!!)
                }
            }
        }
    }

    fun onEvent(bottomSheetMenuEvents: BottomSheetMenuEvents){
        when(bottomSheetMenuEvents){
            is BottomSheetMenuEvents.AddPlaceToMainScreen->{
                viewModelScope.launch {
                    geocodingRepository.insertGeographicalData(bottomSheetMenuEvents.geographicalDataModel)
                }
            }
            is BottomSheetMenuEvents.RemovePlaceFromMainScreen->{
                viewModelScope.launch {
                    geocodingRepository.deleteGeographicalData(bottomSheetMenuEvents.id)
                }
            }
        }
    }
}