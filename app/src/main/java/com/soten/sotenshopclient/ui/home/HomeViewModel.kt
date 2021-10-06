package com.soten.sotenshopclient.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.data.repository.ShoppingRepository
import com.soten.sotenshopclient.data.response.product.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
    private val shoppingApi: ShoppingApi,
) : ViewModel() {

    private val _productListLiveData = MutableLiveData<List<ProductResponse>>()
    val productListLiveData: LiveData<List<ProductResponse>> get() = _productListLiveData

    private val _bannerCurrentPosition = MutableLiveData(0)
    val bannerCurrentPosition: LiveData<Int> get() = _bannerCurrentPosition

    init {
        fetch()
//        test()
    }

    /*private fun test() = viewModelScope.launch {
        try {
            val response = shoppingApi.getAllProductByCategoryId(0, 1)

            Log.d("LogKey_ProductListViewModel", "성공 : $response")
        } catch (e: Exception) {
            Log.d("LogKey_ProductListViewModel", "에러")
        }
    }*/

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