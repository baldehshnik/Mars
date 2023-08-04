package com.firstapplication.mars.domain.repository

import com.firstapplication.mars.domain.model.MarsModel

interface MarsInfoRepository {
    suspend fun getMarsProperties(): List<MarsModel>
}