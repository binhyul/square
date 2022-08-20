package com.example.square.local.source

import com.example.square.local.ProductDao
import com.example.square.local.model.ProductEntity
import javax.inject.Inject

class AppLocalDataSourceImpl @Inject constructor(
    private val productDao: ProductDao
) : AppLocalDataSource {
    override suspend fun getLikeProductList(): List<ProductEntity> = productDao.getProductList()

    override suspend fun findProduct(id: String): ProductEntity? = productDao.getProduct(id)

    override suspend fun likeProduct(product: ProductEntity) = productDao.insertProduct(product)

    override suspend fun unLikeProduct(id: String) = productDao.deleteProduct(id)
}