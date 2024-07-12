package com.myapps.weathernow.ui.searchscreen.suggestion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.myapps.weathernow.ui.common.conditional
import com.myapps.weathernow.ui.common.shimmerEffect
import com.myapps.weathernow.utils.CountryFlagUrl


@Composable
fun SuggestionResultItem(
    modifier: Modifier = Modifier,
    suggestionName: String,
    countryCode: String?
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (countryCode != null) {
            var isLoading by remember{
                mutableStateOf(false)
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(CountryFlagUrl.countryFlag(countryCode))
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .conditional(isLoading){
                       shimmerEffect()
                    },
                onLoading = {isLoading = true},
                onSuccess = {isLoading = false}
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.baseline_image_not_supported_24),
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = suggestionName,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

