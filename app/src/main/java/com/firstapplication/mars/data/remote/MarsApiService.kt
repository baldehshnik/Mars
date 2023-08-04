package com.firstapplication.mars.data.remote

import com.firstapplication.mars.data.model.MarsProperty
import retrofit2.http.GET

const val MARS_URL = "https://android-kotlin-fun-mars-server.appspot.com"

class ReadingException : Exception("An error occurred while reading the data")

interface MarsApiService {
    @GET("photos")
    suspend fun getMarsProperties(): List<MarsProperty>
}