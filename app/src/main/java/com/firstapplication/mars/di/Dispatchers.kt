package com.firstapplication.mars.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Dispatchers @Inject constructor(

    @MainDispatcher
    private val _mainDispatcher: MainCoroutineDispatcher,

    @IODispatcher
    private val _ioDispatcher: CoroutineDispatcher
) {

    val mainDispatcher: MainCoroutineDispatcher get() = _mainDispatcher
    val ioDispatcher: CoroutineDispatcher get() = _ioDispatcher
}