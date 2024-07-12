package com.myapps.weathernow.data.remote.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NetworkObserver @Inject constructor(application: Application){
    private val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        private val _isConnected = MutableStateFlow(false)
        val isConnected: Flow<Boolean> = _isConnected

        init {
            connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: android.net.Network) {
                    _isConnected.value = true
                }

                override fun onLost(network: android.net.Network) {
                    _isConnected.value = false
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    _isConnected.value = false
                }

                override fun onUnavailable() {
                    _isConnected.value = false
                }
            })
        }
}