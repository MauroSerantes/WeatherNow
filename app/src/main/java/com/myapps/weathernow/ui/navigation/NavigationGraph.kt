package com.myapps.weathernow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapps.weathernow.ui.WeatherViewModel
import com.myapps.weathernow.ui.mainscreen.MainScreen
import com.myapps.weathernow.ui.secondscreen.WeatherNextDaysScreen
import com.myapps.weathernow.utils.TimeUpdater


@Composable
fun NavigationGraph(
    viewModel:WeatherViewModel,
    timeUpdater:TimeUpdater,
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainWeatherScreen.route
    ){

        composable(route = Screen.MainWeatherScreen.route){

            MainScreen(
                state = viewModel.uiState.collectAsState().value,
                timeUpdater = timeUpdater,
                navController = navController
            ) {
                viewModel.refresh()
            }
        }

        composable(route = Screen.NextDaysWeatherScreen.route){
            WeatherNextDaysScreen(navController = navController)
        }
    }
}
