package com.myapps.weathernow.ui.mainscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.domain.utils.getErrorDescription
import com.myapps.weathernow.domain.weather.WeatherGeographicalDataModel
import com.myapps.weathernow.ui.StoredGeographicalPlacesState
import com.myapps.weathernow.ui.WeatherState
import com.myapps.weathernow.ui.errors.ErrorSelector
import com.myapps.weathernow.ui.mainscreen.components.WeatherCard
import com.myapps.weathernow.ui.mainscreen.components.LoadingScreen
import com.myapps.weathernow.ui.mainscreen.components.OtherPlacesWeatherForecast
import com.myapps.weathernow.ui.mainscreen.components.WeatherDayForecast
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft
import com.myapps.weathernow.utils.service.WeatherNotificationData


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen(
    state: WeatherState,
    storedDataState:StoredGeographicalPlacesState,
    navController: NavController,
    refreshOnClick: () -> Unit
) {
    when (state) {
        is WeatherState.Loading -> {
            LoadingScreen(circleColor = OceanBlueSoft, loadingText = "Loading Weather Data . . .")
        }

        is WeatherState.Success -> {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                WeatherCard(
                    weatherInfo = state.weatherInfo,
                    onRefreshLocation = { refreshOnClick() }
                )
                Spacer(modifier = Modifier.height(15.dp))
                WeatherDayForecast(
                    weatherInfo = state.weatherInfo,
                    navController = navController,
                    modifier = Modifier.padding(8.dp)
                )
                OtherPlacesWeatherForecast(
                    storedPlacesState = storedDataState,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(50.dp))
            }
        }

        is WeatherState.Error -> {
            ErrorSelector(
                errorMessage = state.errorMessage,
                errorDescription = getErrorDescription(state.errorMessage),
                onRefresh = { refreshOnClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
        }
    }
}





