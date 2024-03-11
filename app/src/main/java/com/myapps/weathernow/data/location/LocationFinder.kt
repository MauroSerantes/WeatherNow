package com.myapps.weathernow.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LastLocationRequest
import com.myapps.weathernow.data.utils.ConnectivityObserver
import com.myapps.weathernow.domain.location.LocationTracker
import com.myapps.weathernow.domain.utils.Errors
import com.myapps.weathernow.utils.DataStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.Error
import java.util.concurrent.Flow
import javax.inject.Inject


class LocationFinder @Inject constructor(
    private val locationClient:FusedLocationProviderClient,
    private val application:Application,
    private val connectivityObserver: ConnectivityObserver
): LocationTracker {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCurrentLocation():DataStatus<Location>{

        val existAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val existAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)


        if(!existAccessCoarseLocationPermission || !existAccessFineLocationPermission){
            return DataStatus.Error(Errors.NO_PERMISSION_GRANTED)
        }

        if(!isGpsEnabled){
            return  DataStatus.Error(Errors.NO_GPS_ACTIVATED)
        }


        return suspendCancellableCoroutine{ cCont->
            locationClient.lastLocation.apply {

                addOnSuccessListener {
                    cCont.resume(DataStatus.Success(result),null)
                }
                addOnFailureListener{
                    cCont.resume(DataStatus.Error(Errors.UNKNOWN_ERROR),null)
                }
                addOnCanceledListener {
                    cCont.cancel()
                }
            }
        }
    }

}