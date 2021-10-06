package com.soten.sotenshopclient.ui.setting.productregister

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charlezz.pickle.data.entity.Media
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_USER_ID
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.repository.FirebaseRepository
import com.soten.sotenshopclient.data.repository.ShoppingRepository
import com.soten.sotenshopclient.data.request.product.ProductRegistrationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ProductRegisterViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val firebaseRepository: FirebaseRepository,
) : ViewModel() {

    private val _productRegisterState = MutableLiveData(ProductRegisterState.NORMAL)
    val productRegisterState: LiveData<ProductRegisterState> get() = _productRegisterState

    fun registerProduct(
        productRegistrationRequest: ProductRegistrationRequest,
        imageList: MutableList<Media>,
    ) = viewModelScope.launch {

        _productRegisterState.value = ProductRegisterState.LOADING

        val userId = sharedPreferenceManager.getInt(KEY_USER_ID)
        if (userId == Int.MIN_VALUE) {
            return@launch
        }

        try {
            val images = firebaseRepository.uploadImagesAsync(imageList).await()

            val register = productRegistrationRequest.copy()
            register.images = images

            val request = shoppingRepository.registerProduct(
                request = register
            )

            if (request.success) {
                _productRegisterState.value = ProductRegisterState.SUCCESS
            } else {
                _productRegisterState.value = ProductRegisterState.FAIL
            }
        } catch (e: Exception) {
            _productRegisterState.value = ProductRegisterState.FAIL
        }
    }

    fun getUserId() = sharedPreferenceManager.getInt(KEY_USER_ID)

}