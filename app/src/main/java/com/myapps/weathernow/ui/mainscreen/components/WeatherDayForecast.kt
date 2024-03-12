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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapps.weathernow.domain.weather.WeatherInfo
import com.myapps.weathernow.ui.navigation.Screen
import com.myapps.weathernow.ui.common.WeatherPerHour
import com.myapps.weathernow.ui.ui.theme.DepthBlue
import com.myapps.weathernow.ui.ui.theme.LightBlue

@Composable
fun WeatherDayForecast(
    weatherInfo:WeatherInfo,
    navController: NavController,
    modifier: Modifier = Modifier
){

   weatherInfo.weatherPerDay[0]?.let {
        Column(modifier = modifier){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("TODAY",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Text(text = "Next 7 days",
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF2E3192),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable(enabled = true){
                            navController.navigate(Screen.NextDaysWeatherScreen.route)
                        }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(content ={
                items(it){
                    WeatherPerHour(
                        time = it.time,
                        icon = it.weatherType.iconRes,
                        temperature = it.temperatureCelsius,
                        modifier = Modifier,
                        isSelected = false
                    )
                }
            })
        }
   }
}


