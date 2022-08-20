package com.example.square.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.square.local.model.ProductEntity


@Database(
    entities = [
        ProductEntity::class
    ],
    version = 1
)
abstract class ProductDataBase : RoomDatabase() {

    abstract fun product(): ProductDao
}