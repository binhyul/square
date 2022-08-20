package com.example.square.local.source

import com.example.square.local.model.ProductEntity


interface AppLocalDataSource {

    suspend fun getLikeProductList(): List<ProductEntity>

    suspend fun findProduct(id: String): ProductEntity?

    suspend fun likeProduct(product: ProductEntity)

    suspend fun unLikeProduct(id: String)
}