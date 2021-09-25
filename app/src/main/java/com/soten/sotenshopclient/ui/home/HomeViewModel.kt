package com.soten.sotenshopclient.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.data.api.ShoppingApi
import com.soten.sotenshopclient.domain.response.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shoppingApi: ShoppingApi,
) : ViewModel() {

    private val _productListLiveData = MutableLiveData<List<ProductResponse>>()
    val productListLiveData get() = _productListLiveData

    init {
        fetch()
    }

    fun fetch() = viewModelScope.launch {
        _productListLiveData.value = shoppingApi.getAllProduct().body()?.data ?: throw IllegalArgumentException("실패")
    }

}