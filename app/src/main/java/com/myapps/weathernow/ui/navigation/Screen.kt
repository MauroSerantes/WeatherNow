package com.myapps.weathernow.ui.navigation

sealed class Screen(val route:String){
    data object MainWeatherScreen: Screen("main_screen")
    data object NextDaysWeatherScreen: Screen("next_screen")
}
