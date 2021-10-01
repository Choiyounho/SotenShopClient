package com.soten.sotenshopclient.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.response.product.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoppingApi: ShoppingApi,
) : ViewModel() {

    private val _productListLiveData = MutableLiveData<List<ProductResponse>>()
    val productListLiveData get() = _productListLiveData

    private val _bannerCurrentPosition = MutableLiveData(0)
    val bannerCurrentPosition get() = _bannerCurrentPosition

    init {
        fetch()
    }

    fun fetch() = viewModelScope.launch {
        _productListLiveData.value =
            shoppingApi.getAllProduct().body()?.data ?: throw IllegalArgumentException("실패")
    }

    fun setBannerCurrentPosition(position: Int) {
        _bannerCurrentPosition.value = position
    }

    fun getBannerCurrentPosition() = _bannerCurrentPosition.value

}