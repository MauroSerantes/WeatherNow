package com.myapps.weathernow.data.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status{
        Available,
        Unavailable,
        Lost,
        Losing
    }

}