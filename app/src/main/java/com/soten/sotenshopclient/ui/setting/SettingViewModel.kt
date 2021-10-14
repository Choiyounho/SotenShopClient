package com.soten.sotenshopclient.ui.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.sotenshopclient.BuildConfig
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_CARD_NAME
import com.soten.sotenshopclient.data.preference.SharedPreferenceKey.KEY_USER_ID
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.data.repository.shopping.ShoppingRepository
import com.soten.sotenshopclient.data.repository.shopping.payment.PaymentRepository
import com.soten.sotenshopclient.data.request.payment.PaymentBillingKeyRequest
import com.soten.sotenshopclient.data.request.shopping.auth.CardNameUpdateRequest
import com.soten.sotenshopclient.ui.basket.BasketViewModel.Companion.SUCCESS_CODE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val sharedPreferenceManager: SharedPreferenceManager,
    private val shoppingRepository: ShoppingRepository,
) : ViewModel() {

    fun registerCard(paymentBillingKeyRequest: PaymentBillingKeyRequest, cardName: String) =
        viewModelScope.launch {
            val tokenResponse =
                paymentRepository.getToken(BuildConfig.IMP_KEY, BuildConfig.IMP_SECRET)

            if (tokenResponse.code != SUCCESS_CODE) {
                return@launch
            }

            val billingKeyMap = hashMapOf(
                "card_number" to paymentBillingKeyRequest.cardNumber,
                "expiry" to paymentBillingKeyRequest.expiry,
                "birth" to paymentBillingKeyRequest.birth,
                "pwd_2digit" to paymentBillingKeyRequest.pwd2digit
            )

            try {
                val response = paymentRepository.registerCard(cardName,
                    tokenResponse.response.accessToken,
                    billingKeyMap)

                if (response.code == 0) {
                    sharedPreferenceManager.putString(KEY_CARD_NAME, cardName)

                    shoppingRepository.registerCard(
                        CardNameUpdateRequest(
                            sharedPreferenceManager.getInt(KEY_USER_ID),
                            cardName
                        )
                    )
                } else {
                    Log.d(TAG, "실패 : $response")
                }
            } catch (e: Exception) {
                Log.d(TAG, "실패")
            }
        }

    companion object {
        private const val TAG = "SettingViewModel"
    }

}