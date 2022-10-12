package com.firstapplication.mars

import android.app.Application
import com.firstapplication.mars.di.AppComponent
import com.firstapplication.mars.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initAppComponent()
    }

    private fun initAppComponent() = DaggerAppComponent.builder()
        .setContext(applicationContext)
        .build()

}