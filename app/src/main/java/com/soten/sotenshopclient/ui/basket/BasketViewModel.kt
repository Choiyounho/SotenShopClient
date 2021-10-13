package com.soten.sotenshopclient.ui.basket

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.BuildConfig
import com.soten.sotenshopclient.data.db.entity.BasketEntity
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.repository.product.basket.ProductBasketRepository
import com.soten.sotenshopclient.data.repository.shopping.payment.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val productBasketRepository: ProductBasketRepository,
    private val paymentRepository: PaymentRepository,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {

    private val _basketProductListLiveData = MutableLiveData<List<BasketEntity>>()
    val basketProductListLiveData: LiveData<List<BasketEntity>> get() = _basketProductListLiveData

    private val _costLiveData = MutableLiveData<Int>()
    val costLiveData: LiveData<Int> get() = _costLiveData

    private val _productCountLiveData = MutableLiveData<Int>()
    val productCountLiveData: LiveData<Int> get() = _productCountLiveData

    init {
        fetchLikedProductList()
    }

    private fun fetchLikedProductList() = viewModelScope.launch {
        _basketProductListLiveData.value = productBasketRepository.getAllBasketProduct()
        fetchAllProductSum()
        _productCountLiveData.value = _basketProductListLiveData.value?.size
    }

    private fun fetchAllProductSum() = viewModelScope.launch {
        var count = 0
        _basketProductListLiveData.value?.forEach {
            count += it.product.price * it.count
        }

        _costLiveData.value = count
    }

    fun deleteBasketEntity(basketEntity: BasketEntity) = viewModelScope.launch {
        productBasketRepository.deleteProduct(basketEntity)
        _basketProductListLiveData.value = productBasketRepository.getAllBasketProduct()
        minusIntLiveData(_productCountLiveData)
        fetchAllProductSum()
    }

    fun onEntityCountPlus(basketEntity: BasketEntity) = viewModelScope.launch {
        productBasketRepository.updateCount(basketEntity.id, basketEntity.count + 1)
        _basketProductListLiveData.value = productBasketRepository.getAllBasketProduct()
        plusIntLiveData(_costLiveData, basketEntity.product.price)
    }

    fun onEntityCountMinus(basketEntity: BasketEntity) = viewModelScope.launch {
        if (basketEntity.count == 1) {
            return@launch
        }
        productBasketRepository.updateCount(basketEntity.id, basketEntity.count - 1)
        _basketProductListLiveData.value = productBasketRepository.getAllBasketProduct()
        minusIntLiveData(_costLiveData)
    }

    private fun plusIntLiveData(liveData: MutableLiveData<Int>, plusNumber: Int = 1) {
        liveData.value = liveData.value?.plus(plusNumber)
    }

    private fun minusIntLiveData(liveData: MutableLiveData<Int>) {
        liveData.value = liveData.value?.minus(1)
    }

    fun paymentGetToken() = viewModelScope.launch {
        try {
            val response = paymentRepository.getToken(
                impKey = BuildConfig.IMP_KEY,
                impSecret = BuildConfig.IMP_SECRET)

            if (response.code == SUCCESS_CODE) {
                val paymentToken = response.response.accessToken
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message!!)
        }
    }

    companion object {
        private const val TAG = "BasketViewModel"

        const val SUCCESS_CODE = 0
    }

}