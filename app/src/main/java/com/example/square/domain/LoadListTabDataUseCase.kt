package com.example.square.domain

import com.example.square.data.AppRepository
import com.example.square.local.model.toModel
import com.example.square.remote.model.ProductItemModel
import com.example.square.remote.model.toModel
import com.example.square.ui.home.model.ListTabModel
import com.example.square.ui.home.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoadListTabDataUseCase @Inject constructor(
    private val appRepository: AppRepository
) : UseCase<Unit, ListTabModel>() {

    override suspend fun execute(parameters: Unit): ListTabModel {
        return withContext(Dispatchers.IO) {
            val data = appRepository.getProductList()
            ListTabModel(
                data.categories.sortedBy { it.order }.map {
                    it.toModel()
                },
                data.productions.sortedBy { it.order }.map {
                    it.findLikeProduct()
                }
            )
        }
    }

    private suspend fun ProductItemModel.findLikeProduct(): ProductModel {
        return if (key != null) {
            val findLikeProduct = appRepository.findProduct(key)?.toModel()
            findLikeProduct ?: toModel(false)
        } else {
            toModel(false)
        }
    }
}


