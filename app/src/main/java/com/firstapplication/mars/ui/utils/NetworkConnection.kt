package com.firstapplication.mars.ui.utils

sealed class NetworkConnection {
    object Connected : NetworkConnection()
    object Disconnected : NetworkConnection()
}
