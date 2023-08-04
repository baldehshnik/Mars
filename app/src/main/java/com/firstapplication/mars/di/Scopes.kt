package com.firstapplication.mars.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class IODispatcher