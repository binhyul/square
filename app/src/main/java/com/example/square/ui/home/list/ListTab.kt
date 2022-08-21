package com.example.square.ui.home.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.square.ui.home.Product
import com.google.gson.Gson
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun ListTab(
    viewModel: ListPageViewModel,
    navController: NavController,
    categoryId: String
) {
    val products by viewModel.products.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 10.dp)
    ) {
        items(products.filter { it.categoryId == categoryId }) { product ->
            Product(productModel = product, clickAction = {
                navController.navigate("detail/${Gson().toJson(product)}")
            }, clickLickAction = {
                viewModel.onClickLikeProduct(product){

                }
            })
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
        }
    }
}