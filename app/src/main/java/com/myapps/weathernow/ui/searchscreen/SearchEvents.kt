package com.myapps.weathernow.ui.searchscreen

import com.myapps.weathernow.domain.geographical.GeographicalDataModel

sealed interface SearchEvents{
    data class  Search(val nameSearching:String):SearchEvents
    data class  StoreGeographicalDataCache(val geographicalDataModel: GeographicalDataModel):SearchEvents
    data object DeleteAllCache:SearchEvents
    data class  DeleteGeographicalDataCache(val id:Long):SearchEvents
}