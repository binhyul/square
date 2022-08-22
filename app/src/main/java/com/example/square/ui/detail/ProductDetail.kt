package com.example.square.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.square.DETAIL_PAGE_LIKE_REFRESH
import com.example.square.R
import com.example.square.ui.resource.Dimens

@Composable
fun ProductDetail(
    navController: NavController,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val product by viewModel.product.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.Product.Detail.pageTopMargin),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.square),
            contentDescription = "thumbnail",
            modifier = Modifier.size(Dimens.Product.productThumbnailSize)
        )
        Text(text = product.name)
        Text(text = stringResource(id = R.string.price, product.price))
        Icon(
            if (product.like) {
                Icons.Filled.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            }, contentDescription = "favorite",
            modifier = Modifier
                .size(Dimens.Product.productLikeIconSize)
                .clickable {
                    viewModel.onClickLikeProduct()
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        DETAIL_PAGE_LIKE_REFRESH,
                        true
                    )
                }
        )
    }
}