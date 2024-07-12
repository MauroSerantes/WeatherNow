package com.myapps.weathernow.ui.placescreen

import com.myapps.weathernow.domain.geographical.GeographicalDataModel

sealed interface BottomSheetMenuEvents{
    data class AddPlaceToMainScreen(val geographicalDataModel: GeographicalDataModel):BottomSheetMenuEvents
    data class RemovePlaceFromMainScreen(val id:Long):BottomSheetMenuEvents
}