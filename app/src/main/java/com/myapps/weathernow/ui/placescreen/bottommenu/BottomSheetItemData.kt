package com.myapps.weathernow.ui.placescreen.bottommenu

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomSheetItemData(
    val icon: ImageVector,
    val contentText:String,
    val actionPerformed:()->Unit
)
