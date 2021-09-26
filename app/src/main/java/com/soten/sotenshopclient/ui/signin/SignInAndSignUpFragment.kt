package com.soten.sotenshopclient.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.soten.sotenshopclient.databinding.FragmentSignInAndSignUpBinding

class SignInAndSignUpFragment : Fragment() {

    private var _binding: FragmentSignInAndSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInAndSignUpBinding.inflate(layoutInflater)
        return binding.root
    }



}