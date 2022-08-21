package com.example.square.domain

import com.example.square.data.AppRepository
import com.example.square.local.model.toModel
import com.example.square.ui.home.model.ProductModel
import com.example.square.ui.home.model.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClickLikeProductUseCase @Inject constructor(
    private val appRepository: AppRepository
) : UseCase<ProductModel, ProductModel>() {

    override suspend fun execute(parameters: ProductModel): ProductModel {
        return withContext(Dispatchers.IO) {
            if (!parameters.like) {
                appRepository.likeProduct(parameters.toEntity())
            } else {
                appRepository.unLikeProduct(parameters.id)
            }

            val productModel = appRepository.findProduct(parameters.id)
            productModel?.toModel()

            productModel?.toModel() ?: parameters.copy(
                like = productModel != null
            )
        }
    }
}