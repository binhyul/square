package com.example.square.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.square.R
import com.example.square.ui.home.model.ProductModel

@Composable
fun ProductDetail(productModel: ProductModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.square),
            contentDescription = "thumbnail",
            modifier = Modifier.size(100.dp)
        )
        Text(text = productModel.name)
        Text(text = stringResource(id = R.string.price, productModel.price))
        Icon(
            if (productModel.like) {
                Icons.Filled.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            }, contentDescription = "favorite",
            modifier = Modifier
                .size(36.dp)
        )
    }
}