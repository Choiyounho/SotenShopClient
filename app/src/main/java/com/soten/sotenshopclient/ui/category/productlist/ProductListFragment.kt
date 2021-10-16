package com.soten.sotenshopclient.ui.category.productlist

import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.adapater.ProductPagedListAdapter
import com.soten.sotenshopclient.databinding.FragmentProductListBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.ui.home.HomeFragment
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

    private val pagerAdapter by lazy {
        ProductPagedListAdapter {
        val bundle = bundleOf(
            HomeFragment.KEY_PRODUCT_ID to it
        )
        findNavController().navigate(R.id.navigationDetailFragment, bundle)
    } }

    override fun initViews() {
        binding.backButton.setOnClickListener {
            productListViewModel.onNormalStateButtonClick()
            findNavController().navigateUp()
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

}