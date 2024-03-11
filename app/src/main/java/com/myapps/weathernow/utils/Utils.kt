package com.myapps.weathernow.utils

import com.myapps.weathernow.domain.weather.WeatherModel
import com.patrykandpatrick.vico.core.entry.FloatEntry

fun convertToChartFloatEntryPoints(
    listOfWeatherModels:List<WeatherModel>
):List<FloatEntry> =
    listOf(
        FloatEntry(0f,listOfWeatherModels[0].temperatureCelsius.toFloat()),
        FloatEntry(1f,listOfWeatherModels[1].temperatureCelsius.toFloat()),
        FloatEntry(2f,listOfWeatherModels[2].temperatureCelsius.toFloat()),
        FloatEntry(3f,listOfWeatherModels[3].temperatureCelsius.toFloat()),
        FloatEntry(4f,listOfWeatherModels[4].temperatureCelsius.toFloat()),
        FloatEntry(5f,listOfWeatherModels[5].temperatureCelsius.toFloat()),
        FloatEntry(6f,listOfWeatherModels[6].temperatureCelsius.toFloat()),
        FloatEntry(7f,listOfWeatherModels[7].temperatureCelsius.toFloat()),
        FloatEntry(8f,listOfWeatherModels[8].temperatureCelsius.toFloat()),
        FloatEntry(9f,listOfWeatherModels[9].temperatureCelsius.toFloat()),
        FloatEntry(10f,listOfWeatherModels[10].temperatureCelsius.toFloat()),
        FloatEntry(11f,listOfWeatherModels[11].temperatureCelsius.toFloat()),
        FloatEntry(12f,listOfWeatherModels[12].temperatureCelsius.toFloat()),
        FloatEntry(13f,listOfWeatherModels[13].temperatureCelsius.toFloat()),
        FloatEntry(14f,listOfWeatherModels[14].temperatureCelsius.toFloat()),
        FloatEntry(15f,listOfWeatherModels[15].temperatureCelsius.toFloat()),
        FloatEntry(16f,listOfWeatherModels[16].temperatureCelsius.toFloat()),
        FloatEntry(17f,listOfWeatherModels[17].temperatureCelsius.toFloat()),
        FloatEntry(18f,listOfWeatherModels[18].temperatureCelsius.toFloat()),
        FloatEntry(19f,listOfWeatherModels[19].temperatureCelsius.toFloat()),
        FloatEntry(20f,listOfWeatherModels[20].temperatureCelsius.toFloat()),
        FloatEntry(21f,listOfWeatherModels[21].temperatureCelsius.toFloat()),
        FloatEntry(22f,listOfWeatherModels[22].temperatureCelsius.toFloat()),
        FloatEntry(23f,listOfWeatherModels[23].temperatureCelsius.toFloat()),
    )