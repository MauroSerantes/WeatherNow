package com.myapps.weathernow.core.modules.qualifiers

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeocodingUrl