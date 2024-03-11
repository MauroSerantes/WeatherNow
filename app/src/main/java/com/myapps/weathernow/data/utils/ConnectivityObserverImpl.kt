package com.myapps.weathernow.data.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.util.logging.Handler
import java.util.logging.LogRecord
import javax.inject.Inject
import javax.net.SocketFactory


class ConnectivityObserverImpl @Inject constructor(
    application: Application
):ConnectivityObserver {

    private val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<ConnectivityObserver.Status> = callbackFlow {

        /*val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .build()*/

        val callback = object : ConnectivityManager.NetworkCallback(){

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                launch { send(ConnectivityObserver.Status.Available) }
            }

            override fun onUnavailable() {
                launch { send(ConnectivityObserver.Status.Unavailable) }
            }

            override fun onLost(network: Network) {
                launch {send(ConnectivityObserver.Status.Lost)}
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                launch { send(ConnectivityObserver.Status.Losing)}
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)
        awaitClose{
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()
}