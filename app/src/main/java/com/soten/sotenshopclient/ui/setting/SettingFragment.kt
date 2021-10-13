package com.soten.sotenshopclient.ui.setting

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.data.preference.SharedPreferenceManager
import com.soten.sotenshopclient.databinding.FragmentSettingBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.ui.setting.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    override var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentSettingBinding.inflate(layoutInflater)

    private val viewModel by activityViewModels<AuthViewModel>()

    @Inject
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    @SuppressLint("SetTextI18n")
    override fun initViews() = with(binding) {
        loginInfoComment1.setOnClickListener {
            findNavController().navigate(R.id.navigationSignInAndSignUpFragment)
        }

        addProductButton.setOnClickListener {
            findNavController().navigate(R.id.navigationProductRegisterFragment)
        }

        signOutButton.setOnClickListener {
            viewModel.signOut()
        }

        cardImage.setOnClickListener {
            findNavController().navigate(R.id.navigationCardRegisterFragment)
        }
    }

    override fun observeData() {
        viewModel.userStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UserState.NORMAL -> handleNormalState() // 비로그인 상태
                UserState.SIGN_IN -> handleSignInState() // 로그인 상태
                UserState.FAIL -> handleNormalState()
                UserState.SUCCESS -> handleSignInState() // 로그인 상태
                UserState.SIGN_UP -> handleSignInState()
                else -> Unit
            }
        }
    }

    private fun handleNormalState() = with(binding) {
        loginInfoComment1.visibility = View.VISIBLE
        loginInfoComment2.visibility = View.VISIBLE

        addProductButton.visibility = View.GONE
        welcomeText.visibility = View.GONE
    }

    private fun handleSignInState() = with(binding) {
        loginInfoComment1.visibility = View.GONE
        loginInfoComment2.visibility = View.GONE

        addProductButton.visibility = View.VISIBLE
        welcomeText.visibility = View.VISIBLE
        welcomeText.text = "${viewModel.getUserName()}님 환영합니다!!"
    }

    /*
    *
        @Field("card_number") card_number: String,
        @Field("expiry") expiry: String,
        @Field("birth") birth: String,
        @Field("pwd_2digit") pwd_2digit: String,
    *
    * */

}