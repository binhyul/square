package com.example.square.remote.model

import com.example.square.ui.home.model.CategoryModel
import com.example.square.ui.home.model.ProductModel
import com.google.gson.annotations.SerializedName


data class ProductResponse(
    @SerializedName("categories") val categories: List<CategoryItemModel>,
    @SerializedName("productions") val productions: List<ProductItemModel>
)

data class CategoryItemModel(
    @SerializedName("key") val key: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("order") val order: Int?,
)

data class ProductItemModel(
    @SerializedName("key") val key: String?,
    @SerializedName("category_key") val categoryKey: String?,
    @SerializedName("price") val price: Int?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("order") val order: Int?,
)

fun CategoryItemModel.toModel() = CategoryModel(
    key ?: throw IllegalStateException("category key is not nullable"),
    name.orEmpty()
)

fun ProductItemModel.toModel(like: Boolean) = ProductModel(
    key ?: throw IllegalStateException("product key is not nullable"),
    categoryKey.orEmpty(),
    price ?: 0,
    name.orEmpty(),
    order ?: 0,
    like
)