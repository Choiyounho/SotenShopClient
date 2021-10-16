package com.soten.sotenshopclient.ui.home.search.searchresult

import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.adapater.ProductPagedListAdapter
import com.soten.sotenshopclient.databinding.FragmentSearchResultBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.ui.home.HomeFragment
import com.soten.sotenshopclient.ui.home.search.SearchFragment.Companion.KEY_KEYWORD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>() {

    override var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentSearchResultBinding.inflate(layoutInflater)

    @Inject
    lateinit var searchResultViewModelFactory: SearchResultViewModel.AssistedFactory

    private val searchResultViewModel = viewModels<SearchResultViewModel> {
        SearchResultViewModel.provideFactory(searchResultViewModelFactory,
            arguments?.getString(KEY_KEYWORD)!!)
    }

    private val pagerAdapter by lazy {
        ProductPagedListAdapter {
            val bundle = bundleOf(
                HomeFragment.KEY_PRODUCT_ID to it
            )
            findNavController().navigate(R.id.navigationDetailFragment, bundle)
        } }

    override fun initViews() {
        binding.viewModel = searchResultViewModel.value
    }

    override fun bindViews() {
        binding.productRecyclerView.adapter = pagerAdapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchResultViewModel.value.fetchProductFlow().collectLatest {
                pagerAdapter.submitData(it)
            }
        }

        pagerAdapter.addLoadStateListener {
            binding.empty.isVisible = pagerAdapter.itemCount == 0
        }
    }

}