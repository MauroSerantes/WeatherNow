package com.myapps.weathernow.ui.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.myapps.weathernow.R
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.ui.common.conditional
import com.myapps.weathernow.ui.common.shimmerEffect
import com.myapps.weathernow.ui.ui.theme.LightBlue
import com.myapps.weathernow.ui.ui.theme.Magenta
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft
import com.myapps.weathernow.ui.ui.theme.SoftGray
import com.myapps.weathernow.utils.CountryFlagUrl
import java.util.Locale.IsoCountryCode

@Composable
fun GeographicalPlaceData(
    modifier: Modifier = Modifier,
    geographicalDataModel: GeographicalDataModel
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.width(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawRoundRect(
                            color = OceanBlueSoft,
                            cornerRadius = CornerRadius(20.0f, 20.0f)
                        )
                    }
                    .border(
                        2.dp, brush = Brush.linearGradient(
                            colorStops = arrayOf(
                                0.15f to LightBlue,
                                0.85f to Magenta
                            )
                        ), shape = RoundedCornerShape(20.0f)
                    )
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    geographicalDataModel.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                if (geographicalDataModel.country != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Country: " + geographicalDataModel.country,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Feature Code: " + geographicalDataModel.featureCode,
                    color = SoftGray, fontSize = 12.sp,
                    maxLines = 1
                )
                if (geographicalDataModel.timezone != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "TZone: " + geographicalDataModel.timezone,
                        color = SoftGray, fontSize = 12.sp,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Latitude: " + geographicalDataModel.latitude + "    " + "Longitude: " + geographicalDataModel.longitude,
                    color = SoftGray,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }
        }
        if (geographicalDataModel.countryCode != null) {
            var isLoading by remember {
                mutableStateOf(false)
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(CountryFlagUrl.countryFlag(geographicalDataModel.countryCode))
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterStart)
                    .conditional(isLoading){
                        shimmerEffect()
                    },
                onLoading = { isLoading = true },
                onSuccess = { isLoading = false }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.baseline_image_not_supported_24),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Alignment.CenterStart)
                    .padding(10.dp)
            )
        }
    }
}


