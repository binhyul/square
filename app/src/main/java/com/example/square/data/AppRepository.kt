package com.example.square.data

import com.example.square.local.model.ProductEntity
import com.example.square.remote.model.ProductResponse

interface AppRepository {

    suspend fun getProductList(): ProductResponse

    suspend fun getLikeProductList(): List<ProductEntity>

    suspend fun findProduct(id: String): ProductEntity?

    suspend fun likeProduct(product: ProductEntity)

    suspend fun unLikeProduct(id: String)
}