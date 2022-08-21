package com.example.square.ui.home.list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.square.domain.ClickLikeProductUseCase
import com.example.square.domain.LoadListTabDataUseCase
import com.example.square.ui.home.model.CategoryModel
import com.example.square.ui.home.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPageViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadListTabDataUseCase: LoadListTabDataUseCase,
    private val clickLikeProductUseCase: ClickLikeProductUseCase
) : ViewModel() {

    val tabs: StateFlow<List<CategoryModel>> = savedStateHandle.getStateFlow(
        LIST_TAB,
        emptyList()
    )

    val products: StateFlow<List<ProductModel>> = savedStateHandle.getStateFlow(
        LIST_PRODUCT,
        emptyList()
    )

    init {
        viewModelScope.launch {
            loadListTabDataUseCase(Unit).runCatching {
                onSuccess {
                    savedStateHandle[LIST_TAB] = it.categories
                    savedStateHandle[LIST_PRODUCT] = it.productList
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

    fun refreshProduct() {
        viewModelScope.launch {
            loadListTabDataUseCase(Unit).runCatching {
                onSuccess {
                    savedStateHandle[LIST_PRODUCT] = it.productList
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }
    }


    companion object {
        const val LIST_TAB = "list_tab"
        const val LIST_PRODUCT = "list_product"
    }
}