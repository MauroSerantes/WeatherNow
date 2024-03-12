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
    val borderColor = if(!isSelected){
        Color.White
    } else Color(0xFF2E3192)

    Card(
        modifier = modifier
            .width(140.dp)
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFFEA8D8D),
                        Color(0xFFA890FE)
                    )
                )
            )
            .border(3.dp, borderColor, RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ){
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = time.toStringFormatterHours(),
                color = Color.Black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text("$temperature °",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

}


