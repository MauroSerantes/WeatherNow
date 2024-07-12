package com.myapps.weathernow.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.myapps.weathernow.ui.navigation.NavigationGraph
import com.myapps.weathernow.ui.ui.theme.DepthBlue
import com.myapps.weathernow.ui.ui.theme.SoftDepthBlue
import com.myapps.weathernow.ui.ui.theme.WeatherNowTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var permissionLocationLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )
        permissionLocationLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.fetchWeatherInformation()
        }

        permissionLocationLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )


        setContent {
            WeatherNowTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colorStops = arrayOf(
                                    0.1f to SoftDepthBlue,
                                    0.9f to DepthBlue
                                )
                            )
                        ),
                    color = Color.Transparent
                ) {
                    NavigationGraph(
                        viewModel = viewModel
                    )
                }
            }

        }
    }
}










