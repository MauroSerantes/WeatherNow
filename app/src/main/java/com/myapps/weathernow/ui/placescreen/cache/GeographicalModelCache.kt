package com.myapps.weathernow.ui.placescreen.cache

import androidx.compose.runtime.mutableStateOf
import com.myapps.weathernow.domain.geographical.GeographicalDataModel

object GeographicalModelCache {
    val geographicalModelCache = mutableStateOf<GeographicalDataModel?>(null)

    val geographicalEmptyDataModel = GeographicalDataModel(
        0,"",0.0,0.0,null,null,null,"",null,null,null,null)
}