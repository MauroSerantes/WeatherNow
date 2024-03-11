package com.myapps.weathernow.utils

import androidx.compose.runtime.State
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun LocalDateTime.toStringFormatter():String{
    var timeStringFormat = this.format(DateTimeFormatter.ofPattern("HH:mm"))

    timeStringFormat = if(this.hour < 12){
        "$timeStringFormat AM"
    }
    else{
        "$timeStringFormat PM"
    }

    return timeStringFormat
}

fun LocalDateTime.toStringFormatterHours():String{
    var timeStringFormat = this.format(DateTimeFormatter.ofPattern("HH"))

    timeStringFormat = if(this.hour < 12){
        "$timeStringFormat AM"
    }
    else{
        "$timeStringFormat PM"
    }

    return timeStringFormat
}

fun State<LocalDateTime>.toStringFormatter():String{
    var timeStringFormat = this.value.format(DateTimeFormatter.ofPattern("HH:mm"))

    timeStringFormat = if(this.value.hour < 12){
        "$timeStringFormat AM"
    }
    else{
        "$timeStringFormat PM"
    }

    return timeStringFormat
}