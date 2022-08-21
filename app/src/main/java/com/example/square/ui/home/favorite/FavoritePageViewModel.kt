package com.example.square.ui.home.favorite

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.square.domain.ClickLikeProductUseCase
import com.example.square.domain.LoadFavoriteTabDataUseCase
import com.example.square.ui.home.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritePageViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadFavoriteTabDataUseCase: LoadFavoriteTabDataUseCase,
    private val clickLikeProductUseCase: ClickLikeProductUseCase
) : ViewModel() {

    val products: StateFlow<List<ProductModel>> = savedStateHandle.getStateFlow(
        FAVORITE_LIST_PRODUCT,
        emptyList()
    )

    val searchText: StateFlow<String> = savedStateHandle.getStateFlow(
        FAVORITE_SEARCH_TEXT,
        ""
    )

    init {
        viewModelScope.launch {
            searchText.collectLatest {
                loadPageData(it)
            }
        }
    }

    private fun loadPageData(text: String) {
        viewModelScope.launch {
            loadFavoriteTabDataUseCase(text).runCatching {
                onSuccess {
                    savedStateHandle[FAVORITE_LIST_PRODUCT] = it
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }
    }

    fun onClickLikeProduct(productModel: ProductModel, update: () -> Unit) {
        viewModelScope.launch {
            clickLikeProductUseCase(productModel).runCatching {
                onSuccess {
                    refreshProduct()
                    update()
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }
    }

    fun onClickClear() {
        savedStateHandle[FAVORITE_SEARCH_TEXT] = ""
    }

    fun onChangeSearchText(text: String) {
        savedStateHandle[FAVORITE_SEARCH_TEXT] = text
    }

     fun refreshProduct() {
         Log.e("favorite ->","refreshProduct")
        loadPageData(searchText.value)
    }


    companion object {
        const val FAVORITE_LIST_PRODUCT = "favorite_list_product"
        const val FAVORITE_SEARCH_TEXT = "search_text"
    }
}