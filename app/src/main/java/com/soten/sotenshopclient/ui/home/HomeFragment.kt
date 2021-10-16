package com.soten.sotenshopclient.ui.home

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.adapater.ItemImage
import com.soten.sotenshopclient.adapater.BannerViewPagerAdapter
import com.soten.sotenshopclient.adapater.ProductAdapter
import com.soten.sotenshopclient.databinding.FragmentHomeBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun getDataBinding() = FragmentHomeBinding.inflate(layoutInflater)

    private val viewModel = viewModels<HomeViewModel>()

    private val productAdapter by lazy {
        ProductAdapter {
            val bundle = bundleOf(
                KEY_PRODUCT_ID to it
            )
            findNavController().navigate(R.id.navigationDetailFragment, bundle)
        }
    }
    private val bannerViewPagerAdapter by lazy { BannerViewPagerAdapter() }

    override fun bindViews() = with(binding) {
        super.bindViews()
        productRecyclerView.adapter = productAdapter
        productRecyclerView.layoutManager = GridLayoutManager(context, 2)

        bannerViewPager.adapter = bannerViewPagerAdapter
        bannerViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        bannerViewPagerAdapter.setImage(
            listOf(
                ItemImage(R.drawable.home_banner_1),
                ItemImage(R.drawable.home_banner_2),
                ItemImage(R.drawable.home_banner_3),
            )
        )

        autoScrollViewPager()
    }

    override fun initViews() {
        binding.settingImage.setOnClickListener {
            findNavController().navigate(R.id.navigationSettingFragment)
        }

        binding.searchTextBox.setOnClickListener {
            findNavController().navigate(R.id.navigationSearchFragment)
        }
    }

    override fun observeData() {
        viewModel.value.productListLiveData.observe(viewLifecycleOwner) {
            productAdapter.submitList(it)
        }

        viewModel.value.bannerCurrentPosition.observe(viewLifecycleOwner) {
            binding.bannerViewPager.currentItem = it
        }
    }

    private fun autoScrollViewPager() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            while (viewLifecycleOwner.lifecycleScope.isActive) {
                delay(BANNER_AUTO_SCROLL_TIME)
                viewModel.value.getBannerCurrentPosition()?.let {
                    viewModel.value.setBannerCurrentPosition(it.plus(NEXT_POSITION) % 3)
                }
            }
        }
    }

    companion object {
        private const val BANNER_AUTO_SCROLL_TIME = 10000L
        private const val NEXT_POSITION = 1

        const val KEY_PRODUCT_ID = "KEY_PRODUCT_ID"
    }

}