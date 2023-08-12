package com.firstapplication.mars.ui.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.firstapplication.mars.ui.utils.NetworkConnection
import com.firstapplication.mars.ui.utils.NetworkStateObservable

class NetworkStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return

        val state = isConnected(context)
        NetworkStateObservable.setNetworkConnection(state)
    }

    @Suppress("DEPRECATION")
    private fun isConnected(context: Context): NetworkConnection {
        var result: NetworkConnection = NetworkConnection.Disconnected
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val state = manager?.getNetworkCapabilities(manager.activeNetwork)
            state?.apply {
                manager.getNetworkCapabilities(manager.activeNetwork)?.apply {
                    if (
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    ) {
                        result = NetworkConnection.Connected
                    }
                }
            }
        } else {
            val state = manager?.activeNetworkInfo
            state?.apply {
                if (type == ConnectivityManager.TYPE_WIFI || type == ConnectivityManager.TYPE_MOBILE) {
                    result = NetworkConnection.Connected
                }
            }
        }

        return result
    }
}