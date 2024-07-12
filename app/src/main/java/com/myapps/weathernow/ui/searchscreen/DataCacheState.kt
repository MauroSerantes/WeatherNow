package com.myapps.weathernow.ui.searchscreen

import com.myapps.weathernow.domain.geographical.GeographicalDataModel

sealed class DataCacheState{
    data object Loading:DataCacheState()
    data class Success(val listOfResults:List<GeographicalDataModel>):DataCacheState()
}
