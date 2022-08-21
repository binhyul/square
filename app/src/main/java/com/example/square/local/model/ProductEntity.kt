package com.example.square.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.square.ui.home.model.ProductModel

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val categoryKey: String?,
    val price: Int?,
    val thumbnail: String?,
    val name: String?,
    val order: Int?
)

fun ProductEntity.toModel() = ProductModel(
    id,
    categoryKey.orEmpty(),
    price ?: 0,
    name.orEmpty(),
    order ?: 0,
    true
)