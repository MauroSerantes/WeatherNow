package com.myapps.weathernow.ui.secondscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapps.weathernow.R
import com.myapps.weathernow.core.cache.SimpleCacheWeatherInfo
import com.myapps.weathernow.ui.secondscreen.components.NextDayWeather
import com.myapps.weathernow.ui.common.WeatherPerHour
import com.myapps.weathernow.ui.ui.theme.LightBlue

@Composable
fun WeatherNextDaysScreen(
    navController: NavController
){

    val weatherInfo = SimpleCacheWeatherInfo.weatherInfoState.value

    if(weatherInfo!=null){
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "back",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(15.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.height(15.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
            ){
                Text("TODAY",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(content ={

                    items(weatherInfo.weatherPerDay[0]!!){
                        if(it == weatherInfo.weatherNow){
                            WeatherPerHour(
                                time = it.time,
                                icon = it.weatherType.iconRes,
                                temperature = it.temperatureCelsius,
                                backgroundColor = LightBlue
                            )
                        }
                        else{
                            WeatherPerHour(
                                time = it.time,
                                icon = it.weatherType.iconRes,
                                temperature = it.temperatureCelsius,
                                backgroundColor = Color.White
                            )
                        }
                    }
                })
                Spacer(modifier = Modifier.height(15.dp))

                Column {
                    for (i in 1..< 7){
                        NextDayWeather(weatherDailyModel = weatherInfo.weatherDaily[i])
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

            }
        }
    }
}