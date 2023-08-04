package com.firstapplication.mars.di

import com.firstapplication.mars.domain.repository.MarsInfoRepository
import com.firstapplication.mars.data.repositories.MarsInfoRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Binds
    fun bindMarsInfoRepositoryImplToMarsInfoRepository(
        marsInfoRepositoryImpl: MarsInfoRepositoryImpl
    ): MarsInfoRepository

}