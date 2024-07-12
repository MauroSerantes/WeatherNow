package com.myapps.weathernow.ui.secondscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.weathernow.domain.weather.WeatherDailyModel
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft
import com.myapps.weathernow.ui.ui.theme.SoftGray


@Composable
fun NextWeatherDay(
    modifier: Modifier = Modifier,
    weatherDailyModel: WeatherDailyModel
) {
    Box(modifier = modifier) {
        Box(
            modifier = modifier
                .width(90.dp)
                .height(300.dp)
        ) {
            Card(
                shape = RoundedCornerShape(50),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(OceanBlueSoft),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)) {
                    Image(
                        painter = painterResource(id = weatherDailyModel.weatherType.iconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = weatherDailyModel.weatherType.weatherDescription,
                        color = SoftGray,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        minLines = 2,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = getDayOfTheWeek(weatherDailyModel.time.dayOfWeek.value),
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = weatherDailyModel.time.dayOfMonth.toString() + " " +
                                weatherDailyModel.time.month.toString(),
                        fontSize = 12.sp,
                        color = SoftGray
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    Row {
                        Text(
                            text = weatherDailyModel.maxTemperature.toInt().toString() + "°",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "/" + weatherDailyModel.minTemperature.toInt().toString() + "°",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }

            }
        }
    }
}


fun getDayOfTheWeek(value: Int): String =
    when (value) {
        1 -> "Mon"
        2 -> "Tue"
        3 -> "Wed"
        4 -> "Thu"
        5 -> "Fri"
        6 -> "Sat"
        7 -> "Sun"
        else -> " "
    }