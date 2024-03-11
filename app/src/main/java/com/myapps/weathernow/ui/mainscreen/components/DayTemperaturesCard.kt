package com.myapps.weathernow.ui.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapps.weathernow.domain.weather.WeatherModel
import com.myapps.weathernow.utils.convertToChartFloatEntryPoints
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.DefaultAlpha
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun DayTemperaturesCard(
    dayWeather:List<WeatherModel>,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.padding(10.dp),
        shape = RoundedCornerShape(15.dp)
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color( 0xFF662D8C),
                        Color(0xFFED1E79)
                    )
                )
            )
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Temperature",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(10.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            TemperatureChart(
                listOfPoints = convertToChartFloatEntryPoints(
                    dayWeather
                )
                , modifier = Modifier
            )
        }
    }
}



@Composable
fun TemperatureChart(
    listOfPoints:List<FloatEntry>,
    modifier: Modifier = Modifier
){

    val modelProducer = remember{ ChartEntryModelProducer() }
    val dataSetForModel = remember { mutableStateListOf(listOf<FloatEntry>()) }
    val dataSetLineSpec = remember { arrayListOf<LineChart.LineSpec>() }

    val scrollState = rememberChartScrollState()


    LaunchedEffect(key1 = true){
        dataSetForModel.clear()
        dataSetLineSpec.clear()
        val dataPoints = arrayListOf<FloatEntry>()
        dataSetLineSpec.add(
            LineChart.LineSpec(
                lineColor = Color.White.toArgb(),
                lineBackgroundShader = DynamicShaders.fromBrush(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.White.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                            Color.White.copy(DefaultAlpha.LINE_BACKGROUND_SHADER_END)
                        )
                    )
                )
            )
        )

        dataSetForModel.add(listOfPoints)
        modelProducer.setEntries(dataSetForModel)
    }

    if (dataSetForModel.isNotEmpty()) {
        ProvideChartStyle {
            Chart(
                chart = LineChart(
                    dataSetLineSpec
                ),
                chartModelProducer = modelProducer,
                startAxis = rememberStartAxis(
                    title = "temperatures",
                    tickLength = 0.dp,
                    valueFormatter = { value,_ ->
                        (value.toInt()).toString() + "°C "
                    },
                ),
                bottomAxis = rememberBottomAxis(
                    title = "hours",
                    tickLength = 0.dp,
                    valueFormatter = { value, _ ->
                        if (value < 12) {
                            (value.toInt().toString()) + " AM"
                        } else {
                            (value.toInt().toString()) + " PM"
                        }
                    },
                ),
                chartScrollState = scrollState,
                isZoomEnabled = true
            )
        }
    }
}


