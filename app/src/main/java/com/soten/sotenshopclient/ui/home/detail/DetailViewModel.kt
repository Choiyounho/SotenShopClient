package com.soten.sotenshopclient.ui.home.detail

import android.util.Log
import androidx.lifecycle.*
import com.soten.sotenshopclient.adapater.ItemImage
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

    private val _imageLiveData = MutableLiveData<List<ItemImage<String>>>()
    val imageLiveData: LiveData<List<ItemImage<String>>> = _imageLiveData

    init {
        getProductForId()
    }

    private fun getProductForId() = viewModelScope.launch {
        shoppingRepository.getProductById(id).data.let {
            _productLiveData.value = it

            val images = mutableListOf<ItemImage<String>>()
            it.images.forEach { image ->
                images.add(ItemImage(image))
                Log.d("TestT", image)
            }
            _imageLiveData.value = images
            Log.d("TestT", "d : ${images}")
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