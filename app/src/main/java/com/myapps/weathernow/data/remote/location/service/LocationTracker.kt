package com.myapps.weathernow.data.remote.location.service

import android.location.Location
import com.myapps.weathernow.utils.DataStatus
import com.myapps.weathernow.utils.LocationData
import kotlinx.coroutines.flow.Flow

interface LocationTracker{
    suspend fun getCurrentLocation():DataStatus<LocationData>
}