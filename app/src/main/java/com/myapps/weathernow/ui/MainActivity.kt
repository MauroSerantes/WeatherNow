package com.myapps.weathernow.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.myapps.weathernow.R
import com.myapps.weathernow.ui.common.WeatherPerHour
import com.myapps.weathernow.ui.navigation.NavigationGraph
import com.myapps.weathernow.ui.ui.theme.WeatherNowTheme
import com.myapps.weathernow.utils.TimeUpdater
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var permissionLocationLauncher: ActivityResultLauncher<Array<String>>

    private val timeUpdater = TimeUpdater()

    private val job: Job = CoroutineScope(Dispatchers.IO).launch {
        while(true){
            val currentSeconds = timeUpdater.getCurrentLocalDateTime().value.second
            val delayTime = 60000L - (currentSeconds*1000).toLong()
            delay(delayTime)
            timeUpdater.updateLocalDateTime(LocalDateTime.now())
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLocationLauncher = registerForActivityResult(
          ActivityResultContracts.RequestMultiplePermissions()
        ){
            viewModel.fetchWeatherInformation()
        }

       permissionLocationLauncher.launch(arrayOf(
           Manifest.permission.ACCESS_COARSE_LOCATION,
           Manifest.permission.ACCESS_FINE_LOCATION
       ))



        setContent {
            WeatherNowTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors =
                                listOf(
                                    Color(0xFF93A5CF),
                                    Color(0xFFE4EfE9)
                                )
                            )
                        ),
                    color = Color.Transparent
                ){
                    NavigationGraph(
                        viewModel = viewModel,
                        timeUpdater = timeUpdater
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}







