package com.myapps.weathernow.ui.navigation

import SearchScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapps.weathernow.ui.WeatherViewModel
import com.myapps.weathernow.ui.mainscreen.MainScreen
import com.myapps.weathernow.ui.placescreen.GeographicalPlaceScreen
import com.myapps.weathernow.ui.placescreen.PlacesScreenViewModel
import com.myapps.weathernow.ui.placescreen.cache.GeographicalModelCache
import com.myapps.weathernow.ui.searchscreen.SearchPlacesViewModel
import com.myapps.weathernow.ui.secondscreen.WeatherNextDaysScreen


@Composable
fun NavigationGraph(
    viewModel:WeatherViewModel
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainWeatherScreen.route
    ){

        composable(route = Screen.MainWeatherScreen.route){

            MainScreen(
                state = viewModel.uiState.collectAsStateWithLifecycle().value,
                storedDataState =  viewModel.storedPlacesState.collectAsStateWithLifecycle().value,
                navController = navController,
                refreshOnClick = {
                    viewModel.refresh()
                }
            )
        }

        composable(route = Screen.NextDaysWeatherScreen.route){
            WeatherNextDaysScreen(navController = navController)
        }

        composable(route = Screen.SearchPlacesScreen.route){
            val searchViewModel:SearchPlacesViewModel = hiltViewModel()

            SearchScreen(
                state = searchViewModel.searchState.collectAsStateWithLifecycle().value,
                cacheState = searchViewModel.cacheState.collectAsStateWithLifecycle().value,
                onEvent = {
                          searchViewModel.onEvent(it)
                },
                navController = navController
            )
        }

        composable(route = Screen.GeographicalPlaceScreen.route){
            val placesViewModel:PlacesScreenViewModel = hiltViewModel()

            GeographicalPlaceScreen(
                otherPlacesWeatherState = placesViewModel.otherPlacesWeatherState.collectAsStateWithLifecycle().value,
                geographicalDataModel = GeographicalModelCache.geographicalModelCache.value?:GeographicalModelCache.geographicalEmptyDataModel,
                navController = navController,
                onEvent = {
                    placesViewModel.onEvent(it)
                }
            )
        }
    }
}
