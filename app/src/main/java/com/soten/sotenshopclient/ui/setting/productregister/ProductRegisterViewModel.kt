package com.soten.sotenshopclient.ui.setting.productregister

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charlezz.pickle.data.entity.Media
import com.google.firebase.storage.FirebaseStorage
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_USER_ID
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.repository.FirebaseRepository
import com.soten.sotenshopclient.data.repository.ShoppingRepository
import com.soten.sotenshopclient.data.request.product.ProductRegistrationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class ProductRegisterViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val firebaseRepository: FirebaseRepository,
) : ViewModel() {

    private val _productRegisterState = MutableLiveData(ProductRegisterState.NORMAL)
    val productRegisterState get() = _productRegisterState

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
                val path = firebaseRepository.uploadImagesAsync(imageList).await()

                val register = productRegistrationRequest.copy()
                register.imagePath = path

                val request = shoppingRepository.registerProduct(
                    request = register
                )

                if (request.success.not()) {
                    Log.d(TAG,"성공")
                    _productRegisterState.value = ProductRegisterState.SUCCESS

                } else {
                    Log.d(TAG, "실패1")
                    _productRegisterState.value = ProductRegisterState.FAIL
                }
            } catch (e: Exception) {
                Log.d(TAG, "실패2 ${e.message}")
                _productRegisterState.value = ProductRegisterState.FAIL
            }
        }

    fun getUserId() = sharedPreferenceManager.getInt(KEY_USER_ID)

    companion object {
        private const val TAG = "ProductRegisterViewModel"
    }

}