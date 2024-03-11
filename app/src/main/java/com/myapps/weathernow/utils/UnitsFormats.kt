package com.myapps.weathernow.utils

import java.text.DecimalFormat

fun Float.formatToSinglePrecision(): String = DecimalFormat("#.#").format(this)