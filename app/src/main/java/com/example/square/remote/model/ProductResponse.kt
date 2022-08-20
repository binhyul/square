package com.example.square.remote.model

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