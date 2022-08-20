package com.example.square.remote

import com.example.square.remote.model.ProductResponse
import retrofit2.http.GET

interface AppService {

    @GET("products")
    suspend fun getProduct(): ProductResponse

}

