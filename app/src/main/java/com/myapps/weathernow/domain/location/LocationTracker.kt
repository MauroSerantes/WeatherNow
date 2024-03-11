package com.myapps.weathernow.domain.location

import android.location.Location
import com.myapps.weathernow.utils.DataStatus
import kotlinx.coroutines.flow.Flow

interface LocationTracker{
    suspend fun getCurrentLocation():DataStatus<Location>
}