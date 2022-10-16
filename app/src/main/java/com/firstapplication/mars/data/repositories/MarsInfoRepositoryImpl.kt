package com.firstapplication.mars.data.repositories

import com.firstapplication.mars.data.interfacies.MarsInfoRepository
import com.firstapplication.mars.data.models.MarsProperty
import com.firstapplication.mars.data.remote.MarsApiService
import javax.inject.Inject

class MarsInfoRepositoryImpl @Inject constructor(private val marsApiService: MarsApiService) :
    MarsInfoRepository {

    override suspend fun getMarsProperties(): List<MarsProperty> =
        marsApiService.getMarsProperties()

}