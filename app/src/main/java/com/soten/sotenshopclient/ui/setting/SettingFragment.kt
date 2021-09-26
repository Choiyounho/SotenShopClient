package com.soten.sotenshopclient.ui.setting

import androidx.navigation.fragment.findNavController
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.databinding.FragmentSettingBinding
import com.soten.sotenshopclient.ui.base.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    override var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    override fun getViewBinding() = FragmentSettingBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        loginContainer.setOnClickListener {
            // TODO 로그인 하지 않았을 시 클릭하면 로그인창
            findNavController().navigate(R.id.navigationSignInAndSignUpFragment)
        }
    }

}