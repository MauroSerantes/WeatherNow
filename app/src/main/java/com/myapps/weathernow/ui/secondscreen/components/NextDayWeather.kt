package com.myapps.weathernow.ui.secondscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.weathernow.domain.weather.WeatherDailyModel
import com.myapps.weathernow.ui.ui.theme.LightGray


@Composable
fun NextDayWeather(
    weatherDailyModel: WeatherDailyModel,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column{
            Text(
                getDayOfTheWeek(weatherDailyModel.time.dayOfWeek.value),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = weatherDailyModel.time.dayOfMonth.toString() + " " +
                        weatherDailyModel.time.month.toString(),
                fontSize = 15.sp,
                color = LightGray
            )
        }
        Column {
            Text(
                text = weatherDailyModel.maxTemperature.toString()+ "°",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = weatherDailyModel.minTemperature.toString()+ "°",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = LightGray
            )
        }
        Image(
            painter = painterResource(id = weatherDailyModel.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
        )
    }

}

fun getDayOfTheWeek(value:Int):String =
    when(value){
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> " "
    }