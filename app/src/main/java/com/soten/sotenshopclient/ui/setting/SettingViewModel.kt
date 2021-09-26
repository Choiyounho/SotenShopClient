package com.soten.sotenshopclient.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.repository.ShoppingRepository
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val preferenceManager: SharedPreferenceManager,
    private val shoppingRepository: ShoppingRepository,
) : ViewModel() {

    private val _userStateLiveData = MutableLiveData(UserState.SIGN_IN)
    val userStateLiveData get() = _userStateLiveData

    fun setSignUpState() {
        _userStateLiveData.value = UserState.SIGN_UP
    }

    fun signUp(signUpRequest: SignUpRequest) = viewModelScope.launch {
        val request = shoppingRepository.signUp(signUpRequest)
        if (request.success) {
            _userStateLiveData.value = UserState.SUCCESS
        } else {
            _userStateLiveData.value = UserState.FAIL
        }
    }

    fun putString(key: String, value: String) {
        preferenceManager.putString(key, value)
    }

    fun getString(key: String) = preferenceManager.getString(key)

}