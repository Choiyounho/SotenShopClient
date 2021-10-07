package com.soten.sotenshopclient.ui.home.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.adapater.DetailImageViewPagerAdapter
import com.soten.sotenshopclient.databinding.FragmentDetailBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.ui.home.HomeFragment.Companion.KEY_PRODUCT_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentDetailBinding.inflate(layoutInflater)

    @Inject
    lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

    private val detailViewModel = viewModels<DetailViewModel> {
        DetailViewModel.provideFactory(detailViewModelFactory, arguments?.getInt(KEY_PRODUCT_ID)!!)
    }

    private val viewPagerAdapter by lazy { DetailImageViewPagerAdapter() }

    override fun initViews() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.basketImage.setOnClickListener {
            findNavController().navigate(R.id.navigationBasketFragment)
        }
    }

    override fun bindViews() {
        binding.imageViewPager.apply {
            adapter = viewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    override fun observeData() {
        detailViewModel.value.productLiveData.observe(viewLifecycleOwner) {
            binding.product = it
            binding.viewModel = detailViewModel.value
        }

        detailViewModel.value.imageLiveData.observe(viewLifecycleOwner) {
            viewPagerAdapter.setImage(it)
        }

        detailViewModel.value.toggleLiveData.observe(viewLifecycleOwner) {
            binding.likeImage.isSelected = it
        }
    }

}