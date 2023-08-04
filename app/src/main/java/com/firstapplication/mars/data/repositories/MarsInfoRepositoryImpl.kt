package com.firstapplication.mars.data.repositories

import com.firstapplication.mars.data.model.migrateToMarsModel
import com.firstapplication.mars.data.remote.MarsApiService
import com.firstapplication.mars.data.remote.ReadingException
import com.firstapplication.mars.di.IODispatcher
import com.firstapplication.mars.domain.repository.MarsInfoRepository
import com.firstapplication.mars.domain.model.MarsModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarsInfoRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val marsApiService: MarsApiService
) : MarsInfoRepository {

    override suspend fun getMarsProperties(): List<MarsModel> {
        return withContext(ioDispatcher) {
            try {
                marsApiService.getMarsProperties().map { it.migrateToMarsModel() }
            } catch (e: Exception) {
                throw ReadingException()
            }
        }
    }
}