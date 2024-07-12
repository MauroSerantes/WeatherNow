package com.myapps.weathernow.ui.placescreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.myapps.weathernow.R
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.ui.mainscreen.components.LoadingScreen
import com.myapps.weathernow.ui.placescreen.bottommenu.BottomSheetItemData
import com.myapps.weathernow.ui.placescreen.bottommenu.BottomSheetMenu
import com.myapps.weathernow.ui.placescreen.components.OtherPlaceWeatherCard
import com.myapps.weathernow.ui.secondscreen.components.NextWeatherDay
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft
import kotlinx.coroutines.launch

@Composable
fun GeographicalPlaceScreen(
    otherPlacesWeatherState: OtherPlacesWeatherState,
    geographicalDataModel: GeographicalDataModel,
    navController: NavController,
    onEvent: (BottomSheetMenuEvents) -> Unit
) {

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheetMenu(
            onDismiss = { showSheet = false }, listOfMenuItemsData = listOf(
                BottomSheetItemData(
                    ImageVector.vectorResource(id = R.drawable.baseline_add_24),
                    "Add place to main screen"
                ) {
                    onEvent(BottomSheetMenuEvents.AddPlaceToMainScreen(geographicalDataModel))
                },
                BottomSheetItemData(
                    ImageVector.vectorResource(id = R.drawable.baseline_delete_24),
                    "Remove place from main screen"
                ) {
                    onEvent(BottomSheetMenuEvents.RemovePlaceFromMainScreen(geographicalDataModel.id))
                }),
            containerColor = OceanBlueSoft
        )
    }

    when (otherPlacesWeatherState) {
        is OtherPlacesWeatherState.Loading -> {
            LoadingScreen(circleColor = OceanBlueSoft, loadingText = "Loading weather data...")
        }

        is OtherPlacesWeatherState.Success -> {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                OtherPlaceWeatherCard(weatherInfoModel = otherPlacesWeatherState.weatherInfoModel,
                    geographicalDataModel = geographicalDataModel,
                    navController = navController,
                    onMenuAction = {
                        showSheet = true
                    })

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..<7) {
                        NextWeatherDay(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp, vertical = 10.dp),
                            weatherDailyModel = otherPlacesWeatherState.weatherInfoModel.weatherDaily[i]
                        )
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }

        is OtherPlacesWeatherState.Error -> {

        }
    }
}