package com.example.square.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.square.domain.ClickLikeProductUseCase
import com.example.square.ui.home.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val clickLikeProductUseCase: ClickLikeProductUseCase
) : ViewModel() {

    val product: StateFlow<ProductModel> = savedStateHandle.getStateFlow(
        PRODUCT_DETAIL,
        ProductModel("", "", 0, "", 0)
    )

    fun onClickLikeProduct(productModel: ProductModel) {
        viewModelScope.launch {
            clickLikeProductUseCase(productModel).runCatching {
                onSuccess {
                    savedStateHandle[PRODUCT_DETAIL] = productModel
                }.onFailure {
                    it.printStackTrace()
                }
            }
        }
    }

    companion object {
        const val PRODUCT_DETAIL = "model"
    }
}