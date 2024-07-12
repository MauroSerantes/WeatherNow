package com.myapps.weathernow.ui.mainscreen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapps.weathernow.R
import com.myapps.weathernow.ui.StoredGeographicalPlacesState
import com.myapps.weathernow.ui.common.LoadingProgress
import com.myapps.weathernow.ui.navigation.Screen
import com.myapps.weathernow.ui.placescreen.cache.GeographicalModelCache
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft
import com.myapps.weathernow.ui.ui.theme.TransparentBlack

@Composable
fun OtherPlacesWeatherForecast(
    storedPlacesState: StoredGeographicalPlacesState,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Other Places", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White
            )
            IconButton(onClick = {
                navController.navigate(Screen.SearchPlacesScreen.route)
            }, modifier = Modifier
                .drawBehind {
                    drawCircle(
                        brush = Brush.linearGradient(
                            colorStops = arrayOf(
                                0.15f to LightBlue, 0.85f to Magenta
                            )
                        )
                    )
                }
                .size(40.dp)
                .padding(10.dp)) {
                Icon(
                    painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        when (storedPlacesState) {
            is StoredGeographicalPlacesState.Loading -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    LoadingProgress()
                }
            }

            is StoredGeographicalPlacesState.Success -> {
                if (storedPlacesState.listOfPlaces.isEmpty()) {
                    EmptyDataStorage(
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                        "No stored places"
                    )
                } else {
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (storedPlacesState.prevPage != null) {
                            IconButton(onClick = {
                                storedPlacesState.prevPage.invoke()
                            }, modifier = Modifier
                                .size(65.dp)
                                .padding(10.dp)
                                .drawBehind {
                                    drawCircle(
                                        OceanBlueSoft
                                    )
                                }
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.arrow_back),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                        repeat(storedPlacesState.listOfPlaces.size) { index ->
                            Spacer(modifier = Modifier.width(10.dp))
                            GeographicalPlaceData(
                                geographicalDataModel = storedPlacesState.listOfPlaces[index],
                                modifier = Modifier
                                    .padding(vertical = 8.dp, horizontal = 10.dp)
                                    .clickable {
                                        GeographicalModelCache.geographicalModelCache.value =
                                            storedPlacesState.listOfPlaces[index]
                                        navController.navigate(Screen.GeographicalPlaceScreen.route)
                                    }
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        if (storedPlacesState.nextPage != null) {
                            IconButton(onClick = {
                                storedPlacesState.nextPage.invoke()
                            }, modifier = Modifier
                                .size(65.dp)
                                .padding(10.dp)
                                .drawBehind {
                                    drawCircle(
                                        OceanBlueSoft
                                    )
                                }
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.baseline_arrow_forward_24),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
        }
    }
}
