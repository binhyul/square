package com.example.square.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.square.R
import com.example.square.ui.resource.Dimens.Global.pageMargin
import com.example.square.ui.resource.Dimens.Product.productLikeIconSize
import com.example.square.ui.resource.Dimens.Product.productThumbnailSize
import com.example.square.ui.home.model.ProductModel

@Composable
fun Product(
    productModel: ProductModel, clickAction: () -> Unit, clickLickAction: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = pageMargin)
        .clickable {
            clickAction.invoke()
        }) {
        Image(
            painter = painterResource(id = R.drawable.square),
            contentDescription = "thumbnail",
            modifier = Modifier.size(productThumbnailSize)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = productModel.name)
            Text(text = stringResource(id = R.string.price, productModel.price))
        }
        Icon(
            if (productModel.like) {
                Icons.Filled.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            }, contentDescription = "favorite",
            modifier = Modifier
                .size(productLikeIconSize)
                .align(Alignment.CenterVertically)
                .clickable {
                    clickLickAction.invoke()
                }
        )
    }
}