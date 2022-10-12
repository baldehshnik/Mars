package com.firstapplication.mars.extensions

import android.content.Context
import com.firstapplication.mars.App
import com.firstapplication.mars.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }