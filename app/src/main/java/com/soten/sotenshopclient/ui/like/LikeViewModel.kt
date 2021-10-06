package com.soten.sotenshopclient.ui.like

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.data.db.entity.LikedEntity
import com.soten.sotenshopclient.data.repository.product.basket.ProductBasketRepository
import com.soten.sotenshopclient.data.repository.product.liked.ProductLikedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val productLikedRepository: ProductLikedRepository,
    private val productBasketRepository: ProductBasketRepository
): ViewModel() {

    private val _likedProductListLiveData = MutableLiveData<List<LikedEntity>>()
    val likedProductListLiveData: LiveData<List<LikedEntity>> get() = _likedProductListLiveData

    init {
        fetchLikedProductList()
    }

    fun fetchLikedProductList() = viewModelScope.launch {
        _likedProductListLiveData.value = productLikedRepository.getAllLikedProduct()
    }

}