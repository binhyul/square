package com.example.square.remote.source

import com.example.square.remote.AppService
import com.example.square.remote.model.ProductResponse
import javax.inject.Inject

class AppRemoteDataSourceImpl @Inject constructor(
    private val appService: AppService
) : AppRemoteDataSource {

    override suspend fun getProductList(): ProductResponse = appService.getProduct()
}