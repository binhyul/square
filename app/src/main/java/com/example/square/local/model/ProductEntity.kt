package com.example.square.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

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