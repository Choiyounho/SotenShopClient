package com.soten.sotenshopclient.ui.like

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.adapater.ProductLikedEntityAdapter
import com.soten.sotenshopclient.databinding.FragmentLikeBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeFragment : BaseFragment<FragmentLikeBinding>() {

    override var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentLikeBinding.inflate(layoutInflater)

    private val likedViewModel by viewModels<LikeViewModel>()

    private val likedProductListAdapter by lazy {
        ProductLikedEntityAdapter(
            itemClickListener = {
                val bundle = bundleOf(
                    HomeFragment.KEY_PRODUCT_ID to it
                )
                findNavController().navigate(R.id.navigationDetailFragment, bundle)
            },
            deleteClickListener = {
                likedViewModel.deleteBasketEntity(it)
            })
    }

    override fun initViews() {
        binding.backButton.setOnClickListener { findNavController().navigateUp() }
    }

    override fun bindViews() {
        binding.likedRecyclerView.adapter = likedProductListAdapter
        binding.likedRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun observeData() {
        likedViewModel.likedProductListLiveData.observe(viewLifecycleOwner) {
            likedProductListAdapter.submitList(it)
        }
    }

}