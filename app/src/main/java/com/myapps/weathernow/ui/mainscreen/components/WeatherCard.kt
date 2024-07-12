package com.myapps.weathernow.ui.mainscreen.components

import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.withInfiniteAnimationFrameNanos
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.ContentInfoCompat
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.myapps.weathernow.R
import com.myapps.weathernow.domain.weather.WeatherGeographicalDataModel
import com.myapps.weathernow.domain.weather.WeatherHourlyModel
import com.myapps.weathernow.domain.weather.WeatherInfoModel

import com.myapps.weathernow.ui.secondscreen.components.getDayOfTheWeek
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft
import com.myapps.weathernow.ui.ui.theme.Platinum
import com.myapps.weathernow.ui.ui.theme.TransparentBlack
import com.myapps.weathernow.utils.SharedPreferencesHelper
import com.myapps.weathernow.utils.convertFromMetersToKilometers
import com.myapps.weathernow.utils.service.WeatherNotification
import com.myapps.weathernow.utils.service.WeatherNotificationData
import com.myapps.weathernow.utils.service.WeatherNotificationWork
import com.myapps.weathernow.utils.toStringFormatter
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherCard(
    weatherInfo: WeatherInfoModel,
    modifier: Modifier = Modifier,
    onRefreshLocation: () -> Unit
) {
    val context = LocalContext.current.applicationContext

    var showDialog by remember { mutableStateOf(false) }

    var checkBox by rememberSaveable {
        mutableStateOf(SharedPreferencesHelper.getBooleanData(context, "notificationActivate"))
    }

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


    var isNotificationPermissionGranted by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
            } else true
        )
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted -> isNotificationPermissionGranted = isGranted }
        )


    if (showDialog) {
        Dialog(
            onDismissRequest = {
                showDialog = false
            }
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            color = OceanBlueSoft,
                            cornerRadius = CornerRadius(15f, 15f)
                        )
                    }
                    .padding(20.dp)
            ) {
                Checkbox(checked = checkBox, onCheckedChange = {
                    if(isNotificationPermissionGranted){
                        checkBox = it
                        SharedPreferencesHelper.clearData(context)
                        SharedPreferencesHelper.saveBooleanData(context, "notificationActivate", it)
                        if (it) {
                            WeatherNotificationWork.startPeriodicWork(context)
                        } else {
                            WeatherNotificationWork.cancelCurrentPeriodicWork(context)
                        }
                    }
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Activate Hourly Notifications", fontSize = 16.sp, color = Color.White)
            }

        }
    }


    val colorStops = arrayOf(
        0.15f to LightBlue,
        0.85f to Magenta
    )
    var time by remember {
        mutableStateOf(LocalDateTime.now())
    }

    LaunchedEffect(true) {
        while (true) {
            time = LocalDateTime.now()
            val currentSeconds = time.second
            val delayTime = 60000L - (currentSeconds * 1000).toLong()
            delay(delayTime)
            time = LocalDateTime.now()
        }
    }

    LaunchedEffect(key1 = !isNotificationPermissionGranted){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
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
                        colorStops = colorStops
                    )
                )
                .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {
                    showDialog = true
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
                Text(
                    text = weatherInfo.cityName ?: "Your Location",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onRefreshLocation,
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
                        painterResource(id = R.drawable.baseline_replay_24),
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

            Spacer(modifier = Modifier.height(10.dp))

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





