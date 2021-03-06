package com.soten.sotenshopclient.ui.setting.productregister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charlezz.pickle.data.entity.Media
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_USER_ID
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.repository.firebase.FirebaseRepository
import com.soten.sotenshopclient.data.repository.shopping.ShoppingRepository
import com.soten.sotenshopclient.data.request.shopping.product.ProductRegistrationRequest
import com.soten.sotenshopclient.ui.category.CategoryState
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

    private val _categoryLiveData = MutableLiveData(CategoryState.NORMAL)
    val categoryLiveData: LiveData<CategoryState> get() = _categoryLiveData

    fun registerProduct(
        productRegistrationRequest: ProductRegistrationRequest,
        imageList: MutableList<Media>,
    ) = viewModelScope.launch {

        if (getCategory() == CategoryState.NORMAL.category) {
            _productRegisterState.value = ProductRegisterState.NOT_SELECT
            _productRegisterState.value = ProductRegisterState.NORMAL
            return@launch
        }

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
                _productRegisterState.value = ProductRegisterState.NORMAL
            }
        } catch (e: Exception) {
            _productRegisterState.value = ProductRegisterState.FAIL
            _productRegisterState.value = ProductRegisterState.NORMAL
        }
    }

    fun onTShirtSelect() {
        _categoryLiveData.value = CategoryState.T_SHIRT
    }

    fun onHoodieSelect() {
        _categoryLiveData.value = CategoryState.HOODIE
    }

    fun onJeansSelect() {
        _categoryLiveData.value = CategoryState.JEANS
    }

    fun onShortsSelect() {
        _categoryLiveData.value = CategoryState.SHORTS
    }

    fun onSweaterSelect() {
        _categoryLiveData.value = CategoryState.SWEATER
    }

    fun onTracksSelect() {
        _categoryLiveData.value = CategoryState.TRACKS
    }

    fun getUserId() = sharedPreferenceManager.getInt(KEY_USER_ID)

    fun getCategory() = categoryLiveData.value?.category!!

}