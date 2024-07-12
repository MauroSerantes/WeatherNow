package com.myapps.weathernow.data.remote.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.myapps.weathernow.data.remote.location.service.LocationTracker
import com.myapps.weathernow.domain.utils.Errors
import com.myapps.weathernow.utils.DataStatus
import com.myapps.weathernow.utils.LocationData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import javax.inject.Inject


class LocationFinder @Inject constructor(
    private val locationClient:FusedLocationProviderClient,
    private val application:Application
): LocationTracker {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCurrentLocation():DataStatus<LocationData>{

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
                addOnSuccessListener {location->
                    if(location!=null){
                        val geocoder = Geocoder(application, Locale.getDefault())
                        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1
                        ) {
                            cCont.resume(DataStatus.Success(
                                LocationData(
                                    latitude =location.latitude,
                                    longitude = location.longitude,
                                    cityName = it[0].locality?:null,
                                    countryName = it[0].countryName?:null
                                )
                            ),null)
                        }
                    }
                    else{
                        cCont.resume(DataStatus.Success(null),null)
                    }
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