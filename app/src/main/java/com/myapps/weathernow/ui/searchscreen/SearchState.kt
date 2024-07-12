package com.myapps.weathernow.ui.searchscreen

import com.myapps.weathernow.domain.geographical.GeographicalDataModel

sealed class SearchState {
    data object Loading : SearchState()
    data class Success(
        val listOfResults: List<GeographicalDataModel>
    ) : SearchState()
    data class Error(val errorMessage: String) : SearchState()
}
