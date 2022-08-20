package com.example.square.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.square.local.model.ProductEntity


@Dao
interface ProductDao {

    @Insert
    fun insertProduct(shop: ProductEntity)

    @Query("SELECT * FROM product")
    fun getProductList(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getProduct(id: String): ProductEntity?

    @Query("DELETE FROM product WHERE id = :id")
    fun deleteProduct(id: String)

    @Query("DELETE FROM product")
    fun clear()

}