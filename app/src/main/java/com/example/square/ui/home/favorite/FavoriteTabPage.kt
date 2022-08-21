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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.square.ui.home.Product
import com.google.gson.Gson
import kotlinx.coroutines.channels.Channel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FavoriteTabPage(
    navController: NavController,
    viewModel: FavoritePageViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    val products by viewModel.products.collectAsState()
    var showClearButton by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val searchText by viewModel.searchText.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
                .onFocusChanged { focusState ->
                    showClearButton = (focusState.isFocused)
                }
                .focusRequester(focusRequester)
                .border(width = 4.dp, color = Color.Black),
            value = searchText,
            onValueChange = {
                viewModel.onChangeSearchText(it)
            },
            placeholder = {
                Text(text = "검색어")
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
                .padding(top = 10.dp)
        ) {
            items(products) { product ->
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
}