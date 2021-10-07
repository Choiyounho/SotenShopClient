package com.soten.sotenshopclient.ui.category

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.databinding.FragmentCategoryBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.ui.category.productlist.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentCategoryBinding.inflate(layoutInflater).apply {
        viewModel = productListViewModel
    }

    private val productListViewModel by activityViewModels<ProductListViewModel>()

    override fun initViews() {
        binding.backButton.setOnClickListener { findNavController().navigateUp() }
    }

    override fun observeData() {
        productListViewModel.categoryLiveData.observe(viewLifecycleOwner) {
            if (it != CategoryState.NORMAL) {
                findNavController().navigate(R.id.navigationProductListFragment)
            }
        }
    }

}