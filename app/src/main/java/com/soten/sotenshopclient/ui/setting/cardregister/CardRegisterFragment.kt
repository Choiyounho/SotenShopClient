package com.soten.sotenshopclient.ui.setting.cardregister

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.soten.sotenshopclient.data.request.payment.PaymentBillingKeyRequest
import com.soten.sotenshopclient.databinding.FragmentRegisterCardBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.ui.setting.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardRegisterFragment : BaseFragment<FragmentRegisterCardBinding>() {

    override var _binding: FragmentRegisterCardBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentRegisterCardBinding.inflate(layoutInflater)

    private val settingViewModel by activityViewModels<SettingViewModel>()

    override fun initViews() {
        binding.backButton.setOnClickListener { findNavController().navigateUp() }

        binding.registerCardButton.setOnClickListener {
            settingViewModel.registerCard(
                PaymentBillingKeyRequest(
                    cardNumber = getCardNumber(),
                    expiry = getExpiry(),
                    birth = getBirth(),
                    pwd2digit = getPassword2Digit()
                ),
                getCardName()
            )
        }
    }

    private fun getCardNumber() = binding.cardNumberEditText.text.toString()
    private fun getExpiry() = binding.cardExpiry.text.toString()
    private fun getBirth() = binding.birthdayEditText.text.toString()
    private fun getPassword2Digit() = binding.cardPassword2Digit.text.toString()
    private fun getCardName() = binding.cardNameEditText.text.toString()

}