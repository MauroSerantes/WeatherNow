package com.myapps.weathernow.domain.weather


import androidx.annotation.DrawableRes
import com.myapps.weathernow.R


sealed class WeatherType(
    val weatherDescription: String,
    @DrawableRes
    val iconRes: Int
) {
    data object ClearSkyDay : WeatherType(
        "Clear Sky",
        R.drawable.clear_day
    )

    data object ClearSkyNight : WeatherType(
        "Clear Sky",
        R.drawable.clear_night
    )

    data object MainlyClearDay : WeatherType(
        "Mainly Clear",
        R.drawable.mostly_clear_day
    )

    data object MainlyClearNight : WeatherType(
        "Mainly Clear",
        R.drawable.mostly_clear_night
    )

    data object PartyCloudyDay : WeatherType(
        "Party Cloudy",
        R.drawable.partlycloud_day
    )

    data object PartyCloudyNight : WeatherType(
        "Partly Cloudy",
        R.drawable.partlycloud_night
    )

    data object OvercastDay : WeatherType(
        "Overcast",
        R.drawable.cloudy
    )

    data object OvercastNight : WeatherType(
        "Overcast",
        R.drawable.cloudy
    )

    data object FoggyDay : WeatherType(
        "Foggy",
        R.drawable.foggy
    )

    data object FoggyNight : WeatherType(
        "Foggy",
        R.drawable.foggy
    )

    data object RimeFogDay : WeatherType(
        "Rime Fog",
        R.drawable.foggy
    )

    data object RimeFogNight : WeatherType(
        "Rime Fog",
        R.drawable.foggy
    )

    data object LightDrizzleDay : WeatherType(
        "Light Drizzle",
        R.drawable.drizzle
    )

    data object LightDrizzleNight : WeatherType(
        "Light Drizzle",
        R.drawable.drizzle
    )

    data object ModerateDrizzleDay : WeatherType(
        "Moderate Drizzle",
        R.drawable.lightrain

    )

    data object ModerateDrizzleNight : WeatherType(
        "Moderate Drizzle",
        R.drawable.lightrain
    )

    data object DenseDrizzleDay : WeatherType(
        "Dense Drizzle",
        R.drawable.rain
    )

    data object DenseDrizzleNight : WeatherType(
        "Dense Drizzle",
        R.drawable.rain
    )

    data object LightFreezingDrizzleDay : WeatherType(
        "Light Freezing Frizzle",
        R.drawable.freezing_drizzle_day_
    )

    data object LightFreezingDrizzleNight : WeatherType(
        "Light Freezing Drizzle",
        R.drawable.freezing_drizzle_night
    )

    data object DenseFreezingDrizzleDay : WeatherType(
        "Dense freezing drizzle",
        R.drawable.freezing_drizzle_day_moderate
    )

    data object DenseFreezingDrizzleNight : WeatherType(
        "Dense freezing drizzle",
        R.drawable.freezing_drizzle_night_moderate
    )

    data object SlightRainDay : WeatherType(
        "Slight rain",
        R.drawable.lightrain
    )

    data object SlightRainNight : WeatherType(
        "Slight rain",
        R.drawable.lightrain
    )

    data object ModerateRainDay : WeatherType(
        "Rainy",
        R.drawable.rain
    )

    data object ModerateRainNight : WeatherType(
        "Rainy",
        R.drawable.rain
    )

    data object HeavyRainDay : WeatherType(
        "Heavy rain",
        R.drawable.heavyrain
    )

    data object HeavyRainNight : WeatherType(
        "Heavy rain",
        R.drawable.heavyrain
    )

    data object LightFreezingRainDay : WeatherType(
        " Light freezing rain",
        R.drawable.freezing_rain
    )

    data object LightFreezingRainNight : WeatherType(
        " Light freezing rain",
        R.drawable.freezing_rain
    )

    data object HeavyFreezingRainDay : WeatherType(
        "Heavy freezing rain",
        R.drawable.freezing_rain_heavy
    )

    data object HeavyFreezingRainNight : WeatherType(
        "Heavy freezing rain",
        R.drawable.freezing_rain_heavy
    )


    data object SlightSnowFallDay : WeatherType(
        "Slight snow fall",
        R.drawable.snow_light
    )

    data object SlightSnowFallNight : WeatherType(
        "Slight snow fall",
        R.drawable.snow_light
    )

    data object ModerateSnowFallDay : WeatherType(
        "Moderate snow fall",
        R.drawable.snow
    )

    data object ModerateSnowFallNight : WeatherType(
        "Moderate snow fall",
        R.drawable.snow
    )

    data object HeavySnowFallDay : WeatherType(
        "Heavy snow fall",
        R.drawable.snow_heavy
    )

    data object HeavySnowFallNight : WeatherType(
        "Heavy snow fall",
        R.drawable.snow_heavy
    )


    data object SnowGrainsDay : WeatherType(
        "Snow grains",
        R.drawable.snow_grains
    )

    data object SnowGrainsNight : WeatherType(
        "Snow grains",
        R.drawable.snow_grains
    )

    data object SlightRainShowersDay : WeatherType(
        "Slight rain showers",
        R.drawable.rain_showers_day_light
    )

    data object SlightRainShowersNight : WeatherType(
        "Slight rain showers",
        R.drawable.rain_showers_night_light
    )

    data object ModerateRainShowersDay : WeatherType(
        "Moderate rain showers",
        R.drawable.rain_showers_day_medium
    )

    data object ModerateRainShowersNight : WeatherType(
        "Moderate rain showers",
        R.drawable.rain_showers_night_medium
    )

    data object ViolentRainShowersDay : WeatherType(
        "Violent rain showers",
        R.drawable.rain_showers_day_heavy
    )

    data object ViolentRainShowersNight : WeatherType(
        "Violent rain showers",
        R.drawable.rain_showers_night_heavy
    )

    data object SlightSnowShowersDay : WeatherType(
        "Light snow showers",
        R.drawable.snow_showers
    )

    data object SlightSnowShowersNight : WeatherType(
        "Light snow showers",
        R.drawable.snow_showers
    )

    data object HeavySnowShowersDay : WeatherType(
        "Heavy snow showers",
        R.drawable.snow_showers
    )

    data object HeavySnowShowersNight : WeatherType(
        "Heavy snow showers",
        R.drawable.snow_showers
    )

    data object ModerateThunderstormDay : WeatherType(
        "Moderate thunderstorm",
        R.drawable.thunderstorm
    )

    data object ModerateThunderstormNight : WeatherType(
        "Moderate thunderstorm",
        R.drawable.thunderstorm
    )

    data object SlightHailThunderstormDay : WeatherType(
        "Thunderstorm with slight hail",
        R.drawable.thunderstorm
    )

    data object SlightHailThunderstormNight : WeatherType(
        "Thunderstorm with slight hail",
        R.drawable.thunderstorm
    )

    data object HeavyHailThunderstormDay : WeatherType(
        "Thunderstorm with heavy hail",
        R.drawable.thunderstorm
    )

    data object HeavyHailThunderstormNight : WeatherType(
        "Thunderstorm with heavy hail",
        R.drawable.thunderstorm
    )
}



fun getWeatherTypeBaseOnWMO(code: Int, isDay: Boolean): WeatherType {
    return when (code) {
        0 -> {
            if (isDay) WeatherType.ClearSkyDay
            else WeatherType.ClearSkyNight

        }

        1 -> {
            if (isDay) WeatherType.MainlyClearDay
            else WeatherType.MainlyClearNight
        }

        2 -> {
            if (isDay) WeatherType.PartyCloudyDay
            else WeatherType.PartyCloudyNight
        }

        3 -> {
            if (isDay) WeatherType.OvercastDay
            else WeatherType.OvercastNight
        }

        45 -> {
            if (isDay) WeatherType.FoggyDay
            else WeatherType.FoggyNight
        }

        48 -> {
            if (isDay) WeatherType.RimeFogDay
            else WeatherType.RimeFogNight
        }

        51 -> {
            if (isDay) WeatherType.LightDrizzleDay
            else WeatherType.LightDrizzleNight
        }

        53 -> {
            if (isDay) WeatherType.ModerateDrizzleDay
            else WeatherType.ModerateDrizzleNight
        }

        55 -> {
            if (isDay) WeatherType.DenseDrizzleDay
            else WeatherType.DenseDrizzleNight
        }

        56 -> {
            if (isDay) WeatherType.LightFreezingDrizzleDay
            else WeatherType.LightFreezingDrizzleNight
        }

        57 -> {
            if (isDay) WeatherType.DenseFreezingDrizzleDay
            else WeatherType.DenseFreezingDrizzleNight
        }

        61 -> {
            if (isDay) WeatherType.SlightRainDay
            else WeatherType.SlightRainNight
        }

        63 -> {
            if (isDay) WeatherType.ModerateRainDay
            else WeatherType.ModerateRainNight
        }

        65 -> {
            if (isDay) WeatherType.HeavyRainDay
            else WeatherType.HeavyRainNight
        }

        66 -> {
            if (isDay) WeatherType.LightFreezingRainDay
            else WeatherType.LightFreezingRainNight
        }

        67 -> {
            if (isDay) WeatherType.HeavyFreezingRainDay
            else WeatherType.HeavyFreezingRainNight
        }

        71 -> {
            if (isDay) WeatherType.SlightSnowFallDay
            else WeatherType.SlightSnowFallNight
        }

        73 -> {
            if (isDay) WeatherType.ModerateSnowFallDay
            else WeatherType.ModerateSnowFallNight
        }

        75 -> {
            if (isDay) WeatherType.HeavySnowFallDay
            else WeatherType.HeavySnowFallNight
        }

        77 -> {
            if (isDay) WeatherType.SnowGrainsDay
            else WeatherType.SnowGrainsNight
        }

        80 -> {
            if (isDay) WeatherType.SlightRainShowersDay
            else WeatherType.SlightRainShowersNight
        }

        81 -> {
            if (isDay) WeatherType.ModerateRainShowersDay
            else WeatherType.ModerateRainShowersNight
        }

        82 -> {
            if (isDay) WeatherType.ViolentRainShowersDay
            else WeatherType.ViolentRainShowersNight
        }

        85 -> {
            if (isDay) WeatherType.SlightSnowShowersDay
            else WeatherType.SlightSnowShowersNight
        }

        86 -> {
            if (isDay) WeatherType.HeavySnowShowersDay
            else WeatherType.HeavySnowShowersNight
        }

        95 -> {
            if (isDay) WeatherType.ModerateThunderstormDay
            else WeatherType.ModerateThunderstormNight
        }

        96 -> {
            if (isDay) WeatherType.SlightHailThunderstormDay
            else WeatherType.SlightHailThunderstormNight
        }

        99 -> {
            if (isDay) WeatherType.HeavyHailThunderstormDay
            else WeatherType.HeavyHailThunderstormNight
        }

        else -> WeatherType.ClearSkyDay
    }
}