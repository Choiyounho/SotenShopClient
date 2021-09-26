package com.soten.sotenshopclient.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.adapater.BannerViewPagerAdapter
import com.soten.sotenshopclient.adapater.ProductAdapter
import com.soten.sotenshopclient.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel = viewModels<HomeViewModel>()

    private val productAdapter by lazy { ProductAdapter() }
    private val bannerViewPagerAdapter by lazy { BannerViewPagerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindView()
        observeData()
    }

    private fun bindView() = with(binding) {
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        bannerViewPager.adapter = bannerViewPagerAdapter
        bannerViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        autoScrollViewPager()
    }

    private fun initViews() {
        binding.settingImage.setOnClickListener {
            findNavController().navigate(R.id.navigationSettingFragment)
        }
    }

    private fun observeData() {
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
                    viewModel.value.setBannerCurrentPosition(it.plus(NEXT_POSITION))
                    Log.d(TAG, it.toString())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "HomeFragment"

        private const val BANNER_AUTO_SCROLL_TIME = 3000L
        private const val BANNER_LIST_SIZE = 3
        private const val NEXT_POSITION = 1
    }

}