package com.firstapplication.mars.ui.utils

interface NetworkStateObserver {
    fun onChanged(value: NetworkConnection)
}

object NetworkStateObservable {

    private val observers = mutableSetOf<NetworkStateObserver>()

    private var value: NetworkConnection? = null

    fun observe(observer: NetworkStateObserver) {
        observers.add(observer)
    }

    fun unregister(observer: NetworkStateObserver) {
        observers.remove(observer)
    }

    fun setNetworkConnection(value: NetworkConnection) {
        this.value = value
        notifyObservers()
    }

    private fun notifyObservers() {
        observers.forEach { observer ->
            value?.let {
                observer.onChanged(it)
            }
        }
    }
}