package com.example.square.ui.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ListTabModel(
    val categories: List<CategoryModel>,
    val productList: List<ProductModel>
)

data class CategoryModel(
    val id: String,
    val name: String
)

@Parcelize
data class ProductModel(
    @SerializedName("id") val id: String,
    @SerializedName("categoryId") val categoryId: String,
    @SerializedName("price") val price: Int,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("like") val like: Boolean = false
) : Parcelable
