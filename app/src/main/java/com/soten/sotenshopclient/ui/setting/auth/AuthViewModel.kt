package com.soten.sotenshopclient.ui.setting.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.repository.ShoppingRepository
import com.soten.sotenshopclient.data.request.auth.SignInRequest
import com.soten.sotenshopclient.data.request.auth.SignUpRequest
import com.soten.sotenshopclient.data.response.auth.SignInResponse
import com.soten.sotenshopclient.ui.setting.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val preferenceManager: SharedPreferenceManager,
    private val shoppingRepository: ShoppingRepository,
) : ViewModel() {

    private val _userStateLiveData = MutableLiveData(UserState.SIGN_IN)
    val userStateLiveData get() = _userStateLiveData

    private val _authNotice = MutableLiveData<String>()
    val authNotice get() = _authNotice

    fun getUserState() = userStateLiveData.value

    fun setSignUpState() {
        _userStateLiveData.value = UserState.SIGN_UP
    }

    fun getAuthNotice() = authNotice.value ?: IS_NOT_VALID

    fun signUp(signUpRequest: SignUpRequest) = viewModelScope.launch {

        if (isNotValidSignUp(signUpRequest)) {
            _userStateLiveData.value = UserState.FAIL
            return@launch
        }

        try {
            val request = shoppingRepository.signUp(signUpRequest)
            if (request.success) {
                _userStateLiveData.value = UserState.SUCCESS
                _authNotice.value = SUCCESS_LOGIN
            } else {
                _userStateLiveData.value = UserState.FAIL
            }
        } catch (e: Exception) {
            _userStateLiveData.value = UserState.FAIL
            _authNotice.value = IS_NOT_VALID
        }
    }

    fun signIn(signInRequest: SignInRequest) = viewModelScope.launch {
        if (isNotValidSignIn(signInRequest)) {
            _userStateLiveData.value = UserState.FAIL
            return@launch
        }

        try {
            val request = shoppingRepository.signIn(signInRequest)
            if (request.success) {
                _authNotice.value = SUCCESS_LOGIN
                _userStateLiveData.value = UserState.SUCCESS
                val response = request.data
                saveUserInfo(response)
            } else {
                _userStateLiveData.value = UserState.FAIL
            }
        } catch (e: Exception) {
            _userStateLiveData.value = UserState.FAIL
            _authNotice.value = IS_NOT_VALID
        }
    }

    private fun saveUserInfo(response: SignInResponse) {
        putString(KEY_TOKEN, response.token)
        putString(KEY_REFRESH_TOKEN, response.refreshToken)
        putString(KEY_USER_NAME, response.userName)
        putLong(KEY_USER_ID, response.userId)
    }

    private fun isNotValidSignUp(signUpRequest: SignUpRequest) =
        when {
            signUpRequest.isNotValidEmail() -> isAuthNotice(IS_NOT_VALID_EMAIL)
            signUpRequest.isNotValidPassword() -> isAuthNotice(IS_NOT_VALID_PASSWORD)
            signUpRequest.isNotValidName() -> isAuthNotice(IS_NOT_VALID_NAME)
            else -> isAuthNotice(IS_NOT_VALID).not()
        }

    private fun isAuthNotice(notice: String): Boolean {
        _authNotice.value = notice
        return true
    }

    private fun isNotValidSignIn(request: SignInRequest) =
        when {
            request.isNotValidEmail() -> isAuthNotice(IS_NOT_VALID_EMAIL)
            request.isNotValidPassword() -> isAuthNotice(IS_NOT_VALID_PASSWORD)
            else -> isAuthNotice(IS_NOT_VALID).not()
        }

    private fun putString(key: String, value: String) {
        preferenceManager.putString(key, value)
    }

    private fun putLong(key: String, value: Long) {
        preferenceManager.putLong(key, value)
    }

    companion object {
        private const val IS_NOT_VALID_EMAIL = "이메일 형식이 올바르지 않습니다."
        private const val IS_NOT_VALID_PASSWORD = "비밀번호는 4자 이상 20자 이하로 입력해주세요."
        private const val IS_NOT_VALID_NAME = "이름은 2자 이상 10자 이하로 입력해주세요."
        private const val IS_NOT_VALID = "알수 없는 오류 발생 잠시 후 다시 시도해주세요."

        private const val SUCCESS_LOGIN = "로그인 성공"

        private const val KEY_TOKEN = "KEY_TOKEN"
        private const val KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN"
        private const val KEY_USER_NAME = "KEY_USER_NAME"
        private const val KEY_USER_ID = "KEY_USER_ID"
    }

}