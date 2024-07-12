package com.myapps.weathernow.ui

import com.myapps.weathernow.domain.geographical.GeographicalDataModel

sealed class StoredGeographicalPlacesState {
    data object Loading:StoredGeographicalPlacesState()
    data class Success(
        val listOfPlaces:List<GeographicalDataModel>,
        val nextPage:(()->Unit)?=null,
        val prevPage:(()->Unit)?=null
    ):StoredGeographicalPlacesState()
}