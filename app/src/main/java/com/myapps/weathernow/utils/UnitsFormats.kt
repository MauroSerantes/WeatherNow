package com.myapps.weathernow.utils

import java.text.DecimalFormat

fun Float.formatToSinglePrecision(): String = DecimalFormat("#.#").format(this)

fun Double.convertFromMetersToKilometers():Double =
    this.div(1000)