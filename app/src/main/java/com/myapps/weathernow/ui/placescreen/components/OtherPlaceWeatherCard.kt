package com.myapps.weathernow.ui.placescreen.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapps.weathernow.R
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.domain.weather.WeatherInfoModel
import com.myapps.weathernow.ui.common.charts.HourlyWeatherTemperatureChart
import com.myapps.weathernow.ui.mainscreen.components.WeatherVariables
import com.myapps.weathernow.ui.secondscreen.components.getDayOfTheWeek
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta
import com.myapps.weathernow.ui.ui.theme.Platinum
import com.myapps.weathernow.ui.ui.theme.TransparentBlack
import com.myapps.weathernow.utils.convertFromMetersToKilometers
import com.myapps.weathernow.utils.toStringFormatter
import java.time.LocalDateTime
import java.time.ZoneId


@Composable
fun OtherPlaceWeatherCard(
    weatherInfoModel: WeatherInfoModel,
    geographicalDataModel: GeographicalDataModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    onMenuAction: () -> Unit
) {
    val colorStops = arrayOf(
        0.15f to LightBlue,
        0.85f to Magenta
    )


    val time by remember {
        mutableStateOf<LocalDateTime>(
            if (geographicalDataModel.timezone != null) {
                LocalDateTime.now(ZoneId.of(geographicalDataModel.timezone))
            } else {
                LocalDateTime.now()
            }
        )
    }


    val weatherNow by remember {
        mutableStateOf(
            if (time.hour == 23 && time.minute >= 30) {
                weatherInfoModel.weatherPerDay[1]?.get(0)
            } else {
                weatherInfoModel.weatherPerDay[0]?.find {
                    (it.time.hour == time.hour && time.minute < 30) ||
                            (it.time.hour == time.hour + 1)
                }
            }
        )
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 35.dp),
        elevation = CardDefaults.elevatedCardElevation(15.dp)
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
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigateUp()
                },
                    modifier = Modifier
                        .drawBehind {
                            drawCircle(
                                TransparentBlack
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = geographicalDataModel.name,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (geographicalDataModel.country != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = geographicalDataModel.country,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                IconButton(onClick = {
                    onMenuAction()
                },
                    modifier = Modifier
                        .drawBehind {
                            drawCircle(
                                TransparentBlack
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

            Text(
                text = weatherNow?.weatherType?.weatherDescription!!,
                color = Platinum,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${weatherNow?.temperatureCelsius}°",
                color = Platinum,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                modifier = Modifier.size(170.dp),
                painter = painterResource(id = weatherNow?.weatherType?.iconRes!!),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = weatherNow?.time?.dayOfWeek?.value?.let { getDayOfTheWeek(it) } + ", " +
                        weatherNow?.time?.dayOfMonth.toString() + "  " +
                        weatherNow?.time?.month.toString() + " | " +
                        time.toStringFormatter(),
                color = Color.White,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            HourlyWeatherTemperatureChart(
                weatherHourly = weatherInfoModel.weatherPerDay[0] ?: emptyList()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherVariables(
                    value = weatherNow?.visibility?.convertFromMetersToKilometers()!!,
                    units = "Km",
                    icon = ImageVector.vectorResource(R.drawable.baseline_visibility_24),
                    iconTint = Color.White,
                    textStyle = TextStyle(
                        color = Platinum,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(10.dp),
                    weatherVariable = "Visibility",
                )
                WeatherVariables(
                    value = weatherNow?.humidity!!,
                    units = "%",
                    icon = ImageVector.vectorResource(R.drawable.ic_drop),
                    iconTint = Color.White,
                    textStyle = TextStyle(
                        color = Platinum,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(10.dp),
                    weatherVariable = "Humidity"
                )
                WeatherVariables(
                    value = weatherNow?.windSpeed!!,
                    units = "Km/h",
                    icon = ImageVector.vectorResource(R.drawable.ic_wind),
                    iconTint = Color.White,
                    textStyle = TextStyle(
                        color = Platinum,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(10.dp),
                    weatherVariable = "Wind"
                )
                WeatherVariables(
                    value = weatherNow?.precipitationProbability?.toDouble()!!,
                    units = "%",
                    icon = ImageVector.vectorResource(R.drawable.baseline_umbrella_24),
                    iconTint = Color.White,
                    textStyle = TextStyle(
                        color = Platinum,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(10.dp),
                    weatherVariable = "Precipitation"
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
