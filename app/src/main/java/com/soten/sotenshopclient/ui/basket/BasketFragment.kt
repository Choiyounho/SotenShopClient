package com.soten.sotenshopclient.ui.basket

import com.soten.sotenshopclient.databinding.FragmentBasketBinding
import com.soten.sotenshopclient.ui.base.BaseFragment

class BasketFragment : BaseFragment<FragmentBasketBinding>() {

    override var _binding:FragmentBasketBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentBasketBinding.inflate(layoutInflater)



}