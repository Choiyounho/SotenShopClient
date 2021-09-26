package com.soten.sotenshopclient.ui.category

import com.soten.sotenshopclient.databinding.FragmentCategoryBinding
import com.soten.sotenshopclient.ui.base.BaseFragment

class CategoryFragment:
    BaseFragment<FragmentCategoryBinding>() {

    override var _binding: FragmentCategoryBinding? = null

    override fun getViewBinding() = FragmentCategoryBinding.inflate(layoutInflater)


}