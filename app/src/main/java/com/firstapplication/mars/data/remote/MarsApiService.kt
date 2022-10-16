package com.firstapplication.mars.data.remote

import com.firstapplication.mars.data.models.MarsProperty
import retrofit2.http.GET

const val MARS_URL = "https://android-kotlin-fun-mars-server.appspot.com"

interface MarsApiService {
    @GET("realestate")
    suspend fun getMarsProperties(): List<MarsProperty>
}