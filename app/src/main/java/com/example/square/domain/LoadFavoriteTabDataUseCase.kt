package com.example.square.domain

import com.example.square.data.AppRepository
import com.example.square.local.model.toModel
import com.example.square.ui.home.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadFavoriteTabDataUseCase @Inject constructor(
    private val appRepository: AppRepository
) : UseCase<String, List<ProductModel>>() {

    override suspend fun execute(parameters: String): List<ProductModel> {
        return withContext(Dispatchers.IO) {
            val likeProductList = appRepository.getLikeProductList()
            val data = if (parameters.isNotBlank()) {
                likeProductList.filter { it.name?.contains(parameters) == true }
            } else {
                likeProductList
            }
            data.map {
                it.toModel()
            }
        }
    }
}


