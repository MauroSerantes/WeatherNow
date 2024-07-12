package com.myapps.weathernow.ui.mainscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.weathernow.R
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft
import com.myapps.weathernow.ui.ui.theme.SoftGray

@Composable
fun EmptyDataStorage(
    modifier: Modifier = Modifier,
    customText: String
){
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(Color.Transparent),
        border = BorderStroke(2.dp, OceanBlueSoft)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(id = R.drawable.no_places),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = customText, fontSize = 18.sp, color = SoftGray, fontWeight = FontWeight.Bold)
        }
    }
}
