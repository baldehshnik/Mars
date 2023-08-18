package com.firstapplication.mars.data.remote

import com.firstapplication.mars.data.model.MarsProperty

class MarsApiServiceTest(
    private val data: List<MarsProperty> = listOf(),
    private val throwException: Boolean = false
) : MarsApiService {

    override suspend fun getMarsProperties(): List<MarsProperty> {
        return if (throwException) throw Exception() else data
    }
}