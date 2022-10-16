package com.firstapplication.mars.data.interfacies

import com.firstapplication.mars.data.models.MarsProperty

interface MarsInfoRepository {
    suspend fun getMarsProperties(): List<MarsProperty>
}