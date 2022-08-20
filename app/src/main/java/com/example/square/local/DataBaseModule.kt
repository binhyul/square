package com.example.square.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideProductDataBase(@ApplicationContext context: Context): ProductDataBase {
        return Room.databaseBuilder(context, ProductDataBase::class.java, PRODUCT_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDao(database: ProductDataBase): ProductDao {
        return database.product()
    }

    companion object {
        private const val PRODUCT_DATABASE_NAME = "product_data_base"
    }
}