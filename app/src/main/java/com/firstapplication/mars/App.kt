package com.firstapplication.mars

import android.app.Application
import android.content.Context
import com.firstapplication.mars.di.AppComponent
import com.firstapplication.mars.di.DaggerAppComponent

class App : Application() {

    private var _appComponent: AppComponent? = null

    internal val appComponent: AppComponent
        get() = checkNotNull(_appComponent) {
            throw IllegalArgumentException("AppComponent is not initialized")
        }

    override fun onCreate() {
        super.onCreate()
        _appComponent = initAppComponent()
    }

    private fun initAppComponent() = DaggerAppComponent.builder()
        .setContext(applicationContext)
        .build()
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }