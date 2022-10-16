package com.firstapplication.mars.di

import com.firstapplication.mars.data.remote.MARS_URL
import com.firstapplication.mars.data.remote.MarsApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(subcomponents = [ActivitySubComponent::class])
object AppModule {

    @Provides
    @Singleton
    fun provideMarsApiService(): MarsApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(MARS_URL)
            .build()

        return retrofit.create(MarsApiService::class.java)
    }

}