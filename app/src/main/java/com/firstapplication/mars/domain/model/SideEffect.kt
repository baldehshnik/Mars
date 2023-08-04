package com.firstapplication.mars.domain.model

class SideEffect<T>(_value: T) {

    private var value: T? = _value
    fun get(): T? = value.also { value = null }
}