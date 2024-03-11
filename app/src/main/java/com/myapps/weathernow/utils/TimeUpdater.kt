package com.myapps.weathernow.utils


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime


class TimeUpdater {
    private val timeState = MutableStateFlow(LocalDateTime.now())

    fun updateLocalDateTime(localDateTime: LocalDateTime){
        timeState.value = localDateTime
    }

    fun getCurrentLocalDateTime():StateFlow<LocalDateTime> = timeState
}

