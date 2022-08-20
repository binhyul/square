package com.example.square.remote.source

import com.example.square.remote.model.ProductResponse


interface AppRemoteDataSource {

    suspend fun getProductList(): ProductResponse
}