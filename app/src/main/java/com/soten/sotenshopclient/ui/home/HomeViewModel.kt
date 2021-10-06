package com.soten.sotenshopclient.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.db.dao.BasketDao
import com.soten.sotenshopclient.data.db.entity.BasketEntity
import com.soten.sotenshopclient.data.repository.product.basket.ProductBasketRepository
import com.soten.sotenshopclient.data.repository.shopping.ShoppingRepository
import com.soten.sotenshopclient.data.response.product.ProductResponse
import com.soten.sotenshopclient.domain.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
    private val basketRepository: ProductBasketRepository,
) : ViewModel() {

    private val _productListLiveData = MutableLiveData<List<ProductResponse>>()
    val productListLiveData: LiveData<List<ProductResponse>> get() = _productListLiveData

    private val _bannerCurrentPosition = MutableLiveData(0)
    val bannerCurrentPosition: LiveData<Int> get() = _bannerCurrentPosition

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        shoppingRepository.getAllProduct().data.let {
            _productListLiveData.value = it
        }

        basketRepository.insertProduct(
            BasketEntity(
                id = 1,
                ProductModel(
                    19,
                    "https://firebasestorage.googleapis.com/v0/b/soten-shop.appspot.com/o/product%2F1%2F1-21.10.06.17%3A33-image0.png?alt=media&token=0619958c-1bb6-46d0-a326-27786603e32a",
                    "asdczx",
                    1000
                ),
                "20"
            )
        )

        Log.d("TestT", "테스트 ${basketRepository.getAllBasketProduct()}")

    }

    fun setBannerCurrentPosition(position: Int) {
        _bannerCurrentPosition.value = position
    }

    fun getBannerCurrentPosition() = _bannerCurrentPosition.value

}