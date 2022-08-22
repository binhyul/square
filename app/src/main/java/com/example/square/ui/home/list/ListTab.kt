package com.example.square.ui.home.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.square.ui.resource.Dimens.Global.pageTopMargin
import com.example.square.ui.resource.Dimens.Product.productOffset
import com.example.square.ui.component.Product
import com.google.gson.Gson

@Composable
fun ListTab(
    viewModel: ListPageViewModel,
    navController: NavController,
    categoryId: String,
    refresh: () -> Unit
) {
    val products by viewModel.products.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = pageTopMargin)
    ) {
        items(products.filter { it.categoryId == categoryId }) { product ->
            Product(productModel = product, clickAction = {
                navController.navigate("detail/${Gson().toJson(product)}")
            }, clickLickAction = {
                viewModel.onClickLikeProduct(product) {
                    refresh()
                }
            })
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(productOffset)
            )
        }
    }
}