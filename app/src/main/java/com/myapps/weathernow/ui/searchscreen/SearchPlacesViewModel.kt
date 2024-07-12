package com.myapps.weathernow.ui.searchscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.weathernow.domain.GeocodingRepository
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPlacesViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
) : ViewModel() {

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Success(emptyList()))
    val searchState: StateFlow<SearchState> get() = _searchState

    private val _cacheState = MutableStateFlow<DataCacheState>(DataCacheState.Loading)
    val cacheState: StateFlow<DataCacheState> get() = _cacheState

    init {
        fetchGeographicalCacheData()
    }

    private fun fetchGeographicalCacheData(){
        viewModelScope.launch {
            geocodingRepository.getGeographicalDataCache().collect{
                _cacheState.value = DataCacheState.Success(it)
            }
        }
    }

    private fun searchPlaces(searchedName: String) {
        viewModelScope.launch {
            _searchState.value = SearchState.Loading
            geocodingRepository.fetchGeographicalInfo(searchedName).collect {
                if (it.status == Status.SUCCESS) {
                    _searchState.value = SearchState.Success(it.data ?: emptyList())
                } else {
                    _searchState.value =
                        SearchState.Error(it.errorMessage ?: "Something wrong happened")
                }
            }
        }
    }


    fun onEvent(searchEvents: SearchEvents) {
        when (searchEvents) {
            is SearchEvents.Search -> {
                searchPlaces(searchEvents.nameSearching)
            }
            is SearchEvents.StoreGeographicalDataCache->{
                viewModelScope.launch {
                    geocodingRepository.insertGeographicalDataCache(searchEvents.geographicalDataModel)
                }
            }
            is SearchEvents.DeleteGeographicalDataCache->{
                viewModelScope.launch{
                    geocodingRepository.deleteGeographicalDataCache(searchEvents.id)
                }
            }
            is SearchEvents.DeleteAllCache->{
                viewModelScope.launch{
                    geocodingRepository.deleteAllGeographicalDataCache()
                }
            }
        }
    }
}