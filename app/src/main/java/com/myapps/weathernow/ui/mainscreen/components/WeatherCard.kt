package com.myapps.weathernow.ui.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.weathernow.R
import com.myapps.weathernow.domain.weather.WeatherInfo
import com.myapps.weathernow.utils.TimeUpdater
import com.myapps.weathernow.utils.toStringFormatter

@Composable
fun WeatherCard(
    weatherInfo: WeatherInfo,
    timeUpdater:TimeUpdater,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .padding(15.dp),
        shape = RoundedCornerShape(15.dp),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors =
                        listOf(
                            Color(0xFF2E3192),
                            Color(0xFF1BFFFF)
                        )
                    )
                )
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Your Location",
                        color = Color.White
                    )
                }

                Text(
                    text = "Today " +
                            timeUpdater.getCurrentLocalDateTime()
                                .collectAsState()
                                .toStringFormatter(),
                    modifier = Modifier
                        .padding(end = 5.dp),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = "${weatherInfo.weatherNow?.temperatureCelsius}°",
                color = Color.White,
                fontSize = 60.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = weatherInfo.weatherNow?.weatherType?.weatherDescription!!,
                color = Color.White,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                WeatherVariables(
                    value = weatherInfo.weatherNow.pressure,
                    units = "hPa",
                    icon = ImageVector.vectorResource(R.drawable.ic_pressure),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                        .padding(10.dp)
                )
                WeatherVariables(
                    value = weatherInfo.weatherNow.humidity,
                    units = "%",
                    icon = ImageVector.vectorResource(R.drawable.ic_drop),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                        .padding(10.dp)
                )
                WeatherVariables(
                    value = weatherInfo.weatherNow.windSpeed,
                    units = "Km/h",
                    icon = ImageVector.vectorResource(R.drawable.ic_wind),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            weatherInfo.weatherPerDay[0]?.let {
                DayTemperaturesCard(dayWeather = it)
            }
        }
    }
}



