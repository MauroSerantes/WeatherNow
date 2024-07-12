package com.myapps.weathernow.ui.secondscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.weathernow.R
import com.myapps.weathernow.domain.weather.WeatherDailyModel
import com.myapps.weathernow.domain.weather.WeatherHourlyModel
import com.myapps.weathernow.ui.common.charts.HourlyWeatherTemperatureChart
import com.myapps.weathernow.ui.mainscreen.components.WeatherVariables
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta
import com.myapps.weathernow.ui.ui.theme.Platinum
import com.myapps.weathernow.ui.ui.theme.SoftGray

@Composable
fun NextDayWeatherCard(
    modifier: Modifier = Modifier,
    weatherDailyModel: WeatherDailyModel,
    weatherHourlyNextDay:List<WeatherHourlyModel>
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.15f to LightBlue,
                            0.85f to Magenta
                        )
                    )
                )
                .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.
                fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Image(
                    modifier = Modifier
                        .size(120.dp),
                    painter = painterResource(id = weatherDailyModel.weatherType.iconRes),
                    contentDescription = null
                )
                Column{
                    Text(text = "Tomorrow", fontSize = 16.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(5.dp))
                    Row{
                        Text(
                            text = weatherDailyModel.maxTemperature.toInt().toString()+"°",
                            fontSize = 38.sp, fontWeight = FontWeight.Bold, color = Color.White
                        )
                        Text(
                            text = "/" + weatherDailyModel.minTemperature.toInt().toString()+"°",
                            fontSize = 21.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = weatherDailyModel.weatherType.weatherDescription,
                        color = SoftGray,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HourlyWeatherTemperatureChart(weatherHourly = weatherHourlyNextDay)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherVariables(
                    value = weatherDailyModel.windSpeed,
                    units = "Km/h",
                    icon = ImageVector.vectorResource(R.drawable.ic_wind),
                    iconTint = Color.White,
                    textStyle = TextStyle(
                        color = Platinum,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(8.dp),
                    weatherVariable = "Wind"
                )
                WeatherVariables(
                    value = weatherDailyModel.precipitationProbability.toDouble(),
                    units = "%",
                    icon = ImageVector.vectorResource(R.drawable.baseline_umbrella_24),
                    iconTint = Color.White,
                    textStyle = TextStyle(
                        color = Platinum,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(8.dp),
                    weatherVariable = "Precipitation"
                )
                WeatherDateVariables(
                    date = weatherDailyModel.sunrise,
                    icon = ImageVector.vectorResource(id = R.drawable.sunrise),
                    iconTint = Color.White,
                    textStyle = TextStyle(
                        color = Platinum,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(8.dp),
                    weatherVariable = "Sunrise"
                )

                WeatherDateVariables(
                    date = weatherDailyModel.sunset,
                    icon = ImageVector.vectorResource(id = R.drawable.sunset),
                    iconTint = Color.White,
                    textStyle = TextStyle(
                        color = Platinum,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(8.dp),
                    weatherVariable = "Sunset"
                )

            }
        }
    }

}