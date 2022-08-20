package com.example.square.ui.home.model

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson


class AssetParamType: NavType<ProductModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ProductModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ProductModel {
        return Gson().fromJson(value, ProductModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ProductModel) {
        bundle.putParcelable(key, value)
    }
}