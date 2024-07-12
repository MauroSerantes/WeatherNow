package com.myapps.weathernow.ui.searchscreen.cache

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.myapps.weathernow.R
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.ui.navigation.Screen
import com.myapps.weathernow.ui.placescreen.cache.GeographicalModelCache
import com.myapps.weathernow.ui.searchscreen.SearchEvents
import com.myapps.weathernow.ui.ui.theme.SoftGray
import com.myapps.weathernow.utils.CountryFlagUrl

@Composable
fun GeographicalCacheItem(
    geographicalDataModel: GeographicalDataModel,
    modifier: Modifier = Modifier,
    onEvent: (SearchEvents) -> Unit,
    navController: NavController
) {
    Row(
        modifier = modifier
            .clickable {
               GeographicalModelCache.geographicalModelCache.value = geographicalDataModel
               navController.navigate(Screen.GeographicalPlaceScreen.route)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (geographicalDataModel.countryCode != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(CountryFlagUrl.countryFlag(geographicalDataModel.countryCode))
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.baseline_image_not_supported_24),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }

        Column {
            Text(
                text = geographicalDataModel.name,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Latitude: " + geographicalDataModel.latitude +"  "+ "Longitude: " + geographicalDataModel.longitude,
                color = SoftGray,
                fontSize = 12.sp,
                maxLines = 1
            )
        }

        IconButton(
            onClick = {
                onEvent.invoke(SearchEvents.DeleteGeographicalDataCache(geographicalDataModel.id))
            },
            colors = IconButtonDefaults.iconButtonColors(Color.Transparent)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_close_24),
                contentDescription = "",
                tint = Color.White,
                modifier = modifier.padding(5.dp)
            )
        }

    }

}