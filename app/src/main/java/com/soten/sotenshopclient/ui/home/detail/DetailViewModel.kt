package com.soten.sotenshopclient.ui.home.detail

import android.util.Log
import androidx.lifecycle.*
import com.soten.sotenshopclient.adapater.ItemImage
import com.soten.sotenshopclient.data.db.entity.BasketEntity
import com.soten.sotenshopclient.data.db.entity.LikedEntity
import com.soten.sotenshopclient.data.repository.product.basket.ProductBasketRepository
import com.soten.sotenshopclient.data.repository.product.liked.ProductLikedRepository
import com.soten.sotenshopclient.data.repository.shopping.ShoppingRepository
import com.soten.sotenshopclient.data.response.product.ProductResponse
import com.soten.sotenshopclient.util.TimeFormatUtil.createdTimeForRegisterProduct
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class DetailViewModel @AssistedInject constructor(
    private val shoppingRepository: ShoppingRepository,
    private val basketRepository: ProductBasketRepository,
    private val likedRepository: ProductLikedRepository,
    @Assisted private val id: Int,
) : ViewModel() {

    private val _productLiveData = MutableLiveData<ProductResponse?>()
    val productLiveData: LiveData<ProductResponse?> get() = _productLiveData

    private val _imageLiveData = MutableLiveData<List<ItemImage<String>>>()
    val imageLiveData: LiveData<List<ItemImage<String>>> get() = _imageLiveData

    private val _toggleLiveData = MutableLiveData<Boolean>()
    val toggleLiveData: LiveData<Boolean> get() = _toggleLiveData

    init {
        getProductForId()
    }

    private fun getProductForId() = viewModelScope.launch {
        shoppingRepository.getProductById(id).data.let {
            _productLiveData.value = it

            val images = mutableListOf<ItemImage<String>>()
            it.images.forEach { image ->
                images.add(ItemImage(image))
            }
            _imageLiveData.value = images

            fetchLikedButton()
        }
    }

    private fun fetchLikedButton() = viewModelScope.launch {
        likedRepository.getLikedEntityById(id)?.let {
            Log.d("TestT", "d ${likedRepository.getAllLikedProduct()}")
            _toggleLiveData.value = true
        } ?: run {
            _toggleLiveData.value = false
        }
    }

    fun onAddBasketButton() = viewModelScope.launch {
        basketRepository.insertProduct(BasketEntity(
            id = id,
            product = productLiveData.value!!.toModel(),
            createdAt = createdTimeForRegisterProduct(),
        ))
    }

    fun toggleLikeButton() = viewModelScope.launch {
        val likedEntity = LikedEntity(
            id = id,
            product = _productLiveData.value!!.toModel(),
            createdAt = createdTimeForRegisterProduct()
        )
        if (_toggleLiveData.value == true) {
            _toggleLiveData.value = false
            likedRepository.deleteProduct(likedEntity)
            Log.d("TestT", "d ${likedRepository.getAllLikedProduct()}")
        } else {
            _toggleLiveData.value = true
            likedRepository.insertProduct(likedEntity)
            Log.d("TestT", "d ${likedRepository.getAllLikedProduct()}")
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