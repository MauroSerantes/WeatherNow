package com.myapps.weathernow.ui.secondscreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapps.weathernow.R
import com.myapps.weathernow.core.cache.SimpleCacheWeatherInfo
import com.myapps.weathernow.ui.secondscreen.components.NextDayWeatherCard
import com.myapps.weathernow.ui.secondscreen.components.NextWeatherDay
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft

@Composable
fun WeatherNextDaysScreen(
    navController: NavController
) {

    val weatherInfo = SimpleCacheWeatherInfo.weatherInfoState.value

    if (weatherInfo != null) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigateUp()
                },
                    modifier = Modifier
                        .drawBehind {
                            drawCircle(
                                OceanBlueSoft
                            )
                        }
                        .size(40.dp)
                        .padding(10.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.arrow_back),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Text(
                    text = "Next 6 Days",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { },
                    modifier = Modifier
                        .drawBehind {
                            drawCircle(
                                OceanBlueSoft
                            )
                        }
                        .size(40.dp)
                        .padding(10.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.baseline_menu_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            NextDayWeatherCard(
                modifier = Modifier.padding(15.dp),
                weatherDailyModel = weatherInfo.weatherDaily[1],
                weatherHourlyNextDay = weatherInfo.weatherPerDay[1] ?: emptyList()
            )

            Row(modifier = Modifier.horizontalScroll(rememberScrollState())){
                for (i in 2..<7) {
                    NextWeatherDay(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp, vertical = 10.dp),
                        weatherDailyModel = weatherInfo.weatherDaily[i]
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}