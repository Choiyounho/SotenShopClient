package com.soten.sotenshopclient.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.data.repository.shopping.ShoppingRepository
import com.soten.sotenshopclient.data.response.product.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
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
    }

    fun setBannerCurrentPosition(position: Int) {
        _bannerCurrentPosition.value = position
    }

    fun getBannerCurrentPosition() = _bannerCurrentPosition.value

}