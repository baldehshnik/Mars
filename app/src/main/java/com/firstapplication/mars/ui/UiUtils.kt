package com.firstapplication.mars.ui

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> MutableLiveData<T>.setValueWithDispatcher(newValue: T) {
    withContext(Dispatchers.Main) {
        this@setValueWithDispatcher.value = newValue
    }
}