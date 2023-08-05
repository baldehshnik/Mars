package com.firstapplication.mars.ui.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> MutableLiveData<T>.setValueWithMainDispatcher(newValue: T) {
    withContext(Dispatchers.Main) {
        this@setValueWithMainDispatcher.value = newValue
    }
}