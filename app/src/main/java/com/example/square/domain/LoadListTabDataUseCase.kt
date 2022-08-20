package com.example.square.domain

import com.example.square.data.AppRepository
import com.example.square.ui.home.model.CategoryModel
import com.example.square.ui.home.model.ListTabModel
import com.example.square.ui.home.model.ProductModel
import javax.inject.Inject


class LoadListTabDataUseCase @Inject constructor(
    private val appRepository: AppRepository
) : UseCase<Unit, ListTabModel>() {

    override suspend fun execute(parameters: Unit): ListTabModel {
        val data = appRepository.getProductList()
        return ListTabModel(
            data.categories.sortedBy { it.order }.map {
                CategoryModel(
                    it.key ?: throw IllegalStateException("category key is not nullable"),
                    it.name.orEmpty()
                )
            },
            data.productions.sortedBy { it.order }.map {
                ProductModel(
                    it.key ?: throw IllegalStateException("product key is not nullable"),
                    it.categoryKey.orEmpty(),
                    it.price ?: 0,
                    it.name.orEmpty(),
                    it.order ?: 0
                )
            }
        )
    }
}


