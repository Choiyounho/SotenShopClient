package com.soten.sotenshopclient.ui.category.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.soten.sotenshopclient.data.repository.paging.PagingRepository
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse
import com.soten.sotenshopclient.ui.category.CategoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val pagingRepository: PagingRepository,
) : ViewModel() {

    private val _categoryLiveData = MutableLiveData<CategoryState>()
    val categoryLiveData: LiveData<CategoryState> get() = _categoryLiveData

    fun fetchProductLiveData(): LiveData<PagingData<ProductResponse>> {
        return pagingRepository.getPagingData(categoryLiveData.value?.category
            ?: CategoryState.NORMAL.category)
            .cachedIn(viewModelScope)
    }

    fun onNormalStateButtonClick() {
        _categoryLiveData.value = CategoryState.NORMAL
    }

    fun onTShirtStateButtonClick() {
        _categoryLiveData.value = CategoryState.T_SHIRT
    }

    fun onHoodieStateButtonClick() {
        _categoryLiveData.value = CategoryState.HOODIE
    }

    fun onJeansStateButtonClick() {
        _categoryLiveData.value = CategoryState.JEANS
    }

    fun onShortsStateButtonClick() {
        _categoryLiveData.value = CategoryState.SHORTS
    }

    fun onSweaterStateButtonClick() {
        _categoryLiveData.value = CategoryState.SWEATER
    }

    fun onTracksStateButtonClick() {
        _categoryLiveData.value = CategoryState.TRACKS
    }

}