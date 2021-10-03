package com.soten.sotenshopclient.ui.home.detail

import androidx.lifecycle.*
import com.soten.sotenshopclient.adapater.ProductAdapter
import com.soten.sotenshopclient.data.repository.ShoppingRepository
import com.soten.sotenshopclient.data.response.product.ProductResponse
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    private val shoppingRepository: ShoppingRepository,
    @Assisted private val id: Int,
) : ViewModel() {

    private val _productLiveData = MutableLiveData<ProductResponse?>()
    val productLiveData: LiveData<ProductResponse?> get() = _productLiveData

    private val _imageLiveData = MutableLiveData<List<String>>()
    val imageLiveData: LiveData<List<String>> = _imageLiveData

    init {
        getProductForId()
    }

    fun getProductForId() = viewModelScope.launch {
        shoppingRepository.getProductForId(id).data.let {
            _productLiveData.value = it
            val images = (it.images.split(ProductAdapter.SPLIT_DELIMITER) as MutableList<String>)
            images.removeFirst()
            images.removeLast()
            _imageLiveData.value = images
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(id: Int): DetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            id: Int,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }

    }

}