package com.myapps.weathernow.ui.searchscreen.cache

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.ui.searchscreen.SearchEvents
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft

@Composable
fun GeographicalDataCache(
    geographicalDataCache: List<GeographicalDataModel>,
    onEvent: (SearchEvents) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Latest Results",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column {
            repeat(geographicalDataCache.size) { index ->
                GeographicalCacheItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 10.dp),
                    geographicalDataModel = geographicalDataCache[index],
                    onEvent = onEvent,
                    navController = navController
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                onEvent(SearchEvents.DeleteAllCache)
            },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(OceanBlueSoft),
            border = BorderStroke(
                2.dp,
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.15f to LightBlue,
                        0.85f to Magenta
                    )
                )
            ),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Delete all",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
    }

}