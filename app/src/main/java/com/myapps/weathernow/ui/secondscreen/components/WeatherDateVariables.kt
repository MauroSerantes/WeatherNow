package com.myapps.weathernow.ui.secondscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.myapps.weathernow.ui.ui.theme.SoftGray
import com.myapps.weathernow.utils.toStringFormatter
import java.time.LocalDateTime


@Composable
fun WeatherDateVariables(
    modifier: Modifier = Modifier,
    date: LocalDateTime,
    icon: ImageVector,
    iconTint: Color,
    textStyle: TextStyle = TextStyle(),
    weatherVariable: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(25.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = date.toStringFormatter(),
            style = textStyle
        )
        Text(
            text = weatherVariable,
            color = SoftGray
        )
    }
}
