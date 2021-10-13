package com.soten.sotenshopclient.ui.setting.auth

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.data.request.shopping.auth.SignInRequest
import com.soten.sotenshopclient.data.request.shopping.auth.SignUpRequest
import com.soten.sotenshopclient.databinding.FragmentSignInAndSignUpBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.ui.setting.UserState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInAndSignUpFragment : BaseFragment<FragmentSignInAndSignUpBinding>() {

    override var _binding: FragmentSignInAndSignUpBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentSignInAndSignUpBinding.inflate(layoutInflater)

    private val viewModel by activityViewModels<AuthViewModel>()

    override fun initViews() = with(binding) {
        signUpText.setOnClickListener {
            viewModel.setSignUpState()
            nameLayout.visibility = View.VISIBLE
            logoText.text = getString(R.string.logo_text)
            signInAndSignUpButton.text = getString(R.string.signup)
            signUpLayout.visibility = View.GONE
        }

        signInAndSignUpButton.setOnClickListener {
            if (signInAndSignUpButton.text == "SignIn") {
                val signInRequest = SignInRequest(
                    email = binding.emailEditText.text.toString(),
                    password = binding.passwordEditText.text.toString(),
                )
                viewModel.signIn(signInRequest)
            } else {
                val signUpRequest = SignUpRequest(
                    email = binding.emailEditText.text.toString(),
                    password = binding.passwordEditText.text.toString(),
                    name = nameEditText.text.toString(),
                )
                viewModel.signUp(signUpRequest)
            }
        }

    }

    override fun observeData() {
        viewModel.userStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                UserState.NORMAL -> Unit
                UserState.SUCCESS -> {
                    Toast.makeText(context, viewModel.getAuthNotice(), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.toHomeFragment)
                }
                UserState.FAIL -> Toast.makeText(context,
                    viewModel.getAuthNotice(),
                    Toast.LENGTH_SHORT).show()
                else -> Unit
            }
        }
    }

}
