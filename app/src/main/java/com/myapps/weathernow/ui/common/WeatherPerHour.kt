package com.myapps.weathernow.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.weathernow.ui.ui.theme.Gold
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft
import com.myapps.weathernow.ui.ui.theme.Platinum
import com.myapps.weathernow.utils.toStringFormatterHours
import java.time.LocalDateTime

@Composable
fun WeatherPerHour(
    time: LocalDateTime,
    @DrawableRes
    icon:Int,
    temperature:Double,
    isSelected:Boolean,
    modifier: Modifier = Modifier
){

    Card(
        modifier = modifier
            .width(100.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(if(isSelected) Gold else OceanBlueSoft),
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = time.toStringFormatterHours(),
                color = Platinum,
                fontSize = 16.sp
            )
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
            )
            Text("${temperature.toInt()}°",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}


