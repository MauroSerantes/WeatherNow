package com.myapps.weathernow.ui.mainscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapps.weathernow.domain.weather.WeatherHourlyModel
import com.myapps.weathernow.domain.weather.WeatherInfoModel
import com.myapps.weathernow.ui.navigation.Screen
import com.myapps.weathernow.ui.common.WeatherPerHour
import com.myapps.weathernow.ui.ui.theme.BrilliantBlue
import com.myapps.weathernow.ui.ui.theme.LightBlue
import java.time.LocalDateTime

@Composable
fun WeatherDayForecast(
    weatherInfo: WeatherInfoModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val currentTime by remember {
        mutableStateOf<LocalDateTime>(LocalDateTime.now())
    }
    val weatherNow by remember {
        mutableStateOf(
            if (currentTime.hour == 23 && currentTime.minute >= 30) {
                weatherInfo.weatherPerDay[1]?.get(0)
            } else {
                weatherInfo.weatherPerDay[0]?.find {
                    (it.time.hour == currentTime.hour && currentTime.minute < 30) ||
                            (it.time.hour == currentTime.hour + 1)
                }
            }
        )
    }

    weatherInfo.weatherPerDay[0]?.let {
        Column(modifier = modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Today",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(text = "Next 7 days >",
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline,
                    color = BrilliantBlue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable(enabled = true) {
                            navController.navigate(Screen.NextDaysWeatherScreen.route)
                        }
                )
            }
            LazyRow(content = {
                items(weatherInfo.weatherPerDay[0]!!) {
                    if (it == weatherNow) {
                        WeatherPerHour(
                            time = it.time,
                            icon = it.weatherType.iconRes,
                            temperature = it.temperatureCelsius,
                            isSelected = true
                        )
                    } else {
                        WeatherPerHour(
                            time = it.time,
                            icon = it.weatherType.iconRes,
                            temperature = it.temperatureCelsius,
                            isSelected = false
                        )
                    }
                }
            })
        }
    }
}


