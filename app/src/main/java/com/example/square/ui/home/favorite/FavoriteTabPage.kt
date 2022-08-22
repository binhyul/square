package com.example.square.ui.home.favorite

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.square.R
import com.example.square.ui.resource.Dimens.Global.pageMargin
import com.example.square.ui.resource.Dimens.Global.pageTopMargin
import com.example.square.ui.resource.Dimens.Search.searchBarBorder
import com.example.square.ui.component.Product
import com.google.gson.Gson


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FavoriteTabPage(
    navController: NavController,
    viewModel: FavoritePageViewModel,
    refresh: () -> Unit
) {
    val products by viewModel.products.collectAsState()
    var showClearButton by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val searchText by viewModel.searchText.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = pageMargin)
                .onFocusChanged { focusState ->
                    showClearButton = (focusState.isFocused)
                }
                .focusRequester(focusRequester)
                .border(width = searchBarBorder, color = Color.Black),
            value = searchText,
            onValueChange = {
                viewModel.onChangeSearchText(it)
            },
            placeholder = {
                Text(text = stringResource(id = R.string.search_place_holder))
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
            ),
            trailingIcon = {
                AnimatedVisibility(
                    visible = showClearButton,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = { viewModel.onClickClear() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "clear"
                        )
                    }

                }
            },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = pageTopMargin)
        ) {
            items(products) { product ->
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
                        .height(10.dp)
                )
            }
        }
    }
}