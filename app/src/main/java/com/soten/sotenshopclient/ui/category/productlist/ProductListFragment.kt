package com.soten.sotenshopclient.ui.category.productlist

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.soten.sotenshopclient.adapater.ProductPagedListAdapter
import com.soten.sotenshopclient.databinding.FragmentProductListBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : BaseFragment<FragmentProductListBinding>() {

    override var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentProductListBinding.inflate(layoutInflater).apply {
        viewModel = productListViewModel
    }

    private val productListViewModel by activityViewModels<ProductListViewModel>()

    private val pagerAdapter by lazy { ProductPagedListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateUp()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun initViews() {
        binding.backButton.setOnClickListener {
            navigateUp()
        }
    }

    override fun bindViews() {
        binding.productRecyclerView.adapter = pagerAdapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun observeData() {
        productListViewModel.fetchProductLiveData().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                pagerAdapter.submitData(it)
            }
        }
    }

    private fun navigateUp() {
        productListViewModel.onNormalStateButtonClick()
        findNavController().navigateUp()
    }

}