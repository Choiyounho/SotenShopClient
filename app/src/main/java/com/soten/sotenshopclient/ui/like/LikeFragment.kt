package com.soten.sotenshopclient.ui.like

import com.soten.sotenshopclient.databinding.FragmentLikeBinding
import com.soten.sotenshopclient.ui.base.BaseFragment

class LikeFragment : BaseFragment<FragmentLikeBinding>() {

    override var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!
    override fun getViewBinding() = FragmentLikeBinding.inflate(layoutInflater)


}