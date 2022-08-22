package com.example.square.ui.home.model

import android.os.Parcelable
import com.example.square.local.model.ProductEntity
import kotlinx.parcelize.Parcelize

data class ListTabModel(
    val categories: List<CategoryModel>,
    val productList: List<ProductModel>
)

@Parcelize
data class CategoryModel(
    val id: String,
    val name: String
) : Parcelable

@Parcelize
data class ProductModel(
    val id: String = "",
    val categoryId: String = "",
    val price: Int = 0,
    val name: String = "",
    val order: Int = 0,
    val like: Boolean = false
) : Parcelable


fun ProductModel.toEntity() = ProductEntity(
    id,
    categoryId,
    price,
    null,
    name,
    order
)