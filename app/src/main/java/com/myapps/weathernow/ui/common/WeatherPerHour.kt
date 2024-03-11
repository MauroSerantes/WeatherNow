package com.myapps.weathernow.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.myapps.weathernow.ui.ui.theme.LightGray
import com.myapps.weathernow.utils.toStringFormatterHours
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherPerHour(
    time: LocalDateTime,
    @DrawableRes
    icon:Int,
    temperature:Double,
    backgroundColor: Color,
    modifier: Modifier = Modifier
){
    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(15.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 15.dp,
        backgroundColor = backgroundColor
    ){
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = time.toStringFormatterHours(),
                color = LightGray,
                fontSize = 18.sp
            )
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp))
            Text("$temperature °",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


