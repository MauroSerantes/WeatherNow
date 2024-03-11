package com.myapps.weathernow.ui.mainscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapps.weathernow.domain.utils.getErrorDescription
import com.myapps.weathernow.ui.WeatherState
import com.myapps.weathernow.ui.errors.ErrorCard
import com.myapps.weathernow.ui.mainscreen.components.WeatherCard
import com.myapps.weathernow.ui.mainscreen.components.LoadingScreen
import com.myapps.weathernow.ui.mainscreen.components.WeatherDayForecast
import com.myapps.weathernow.utils.TimeUpdater

@Composable
fun MainScreen(
    state: WeatherState,
    timeUpdater: TimeUpdater,
    navController: NavController,
    refreshOnErrorClick:() -> Unit
){
    when(state){
        is WeatherState.Loading->{
            LoadingScreen(loadingText = "Loading Weather Data . . .")
        }

        is WeatherState.Success->{
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
            ){
                WeatherCard(
                    weatherInfo = state.weatherInfo,
                    timeUpdater = timeUpdater)
                Spacer(modifier = Modifier.height(5.dp))
                WeatherDayForecast(weatherInfo = state.weatherInfo,navController)
            }
        }
        is WeatherState.Error->{
            ErrorCard(
                errorMessage = state.errorMessage,
                errorDescription = getErrorDescription(state.errorMessage),
                onClickButton = refreshOnErrorClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        RoundedCornerShape(15.dp)
                    )

            )
        }
        is WeatherState.Refresh->{
            LoadingScreen(loadingText = "Updating . . .")
        }
    }
}