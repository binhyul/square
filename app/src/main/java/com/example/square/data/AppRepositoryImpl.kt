package com.example.square.data

import com.example.square.local.model.ProductEntity
import com.example.square.local.source.AppLocalDataSource
import com.example.square.remote.model.ProductResponse
import com.example.square.remote.source.AppRemoteDataSource
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val dataSource: AppRemoteDataSource,
    private val localDataSource: AppLocalDataSource
) : AppRepository {

    override suspend fun getProductList(): ProductResponse = dataSource.getProductList()

    override suspend fun getLikeProductList(): List<ProductEntity> =
        localDataSource.getLikeProductList()

    override suspend fun findProduct(id: String): ProductEntity? = localDataSource.findProduct(id)

    override suspend fun likeProduct(product: ProductEntity) = localDataSource.likeProduct(product)

    override suspend fun unLikeProduct(id: String) = localDataSource.unLikeProduct(id)
}