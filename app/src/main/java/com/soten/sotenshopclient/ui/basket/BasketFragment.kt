package com.soten.sotenshopclient.ui.basket

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.adapater.ProductBasketEntityAdapter
import com.soten.sotenshopclient.data.request.payment.PaymentRequest
import com.soten.sotenshopclient.databinding.FragmentBasketBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>() {

    override var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentBasketBinding.inflate(layoutInflater)

    private val basketViewModel by viewModels<BasketViewModel>()

    private val basketAdapter by lazy {
        ProductBasketEntityAdapter(
            itemClickListener = {
                val bundle = bundleOf(HomeFragment.KEY_PRODUCT_ID to it)
                findNavController().navigate(R.id.navigationDetailFragment, bundle)
            },
            deleteClickListener = {
                basketViewModel.deleteBasketEntity(it)
            },
            plusClickListener = {
                basketViewModel.onEntityCountPlus(it)
            },
            minusClickListener = {
                basketViewModel.onEntityCountMinus(it)
            }
        )
    }

    override fun initViews() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.checkoutButton.setOnClickListener {
            basketViewModel.paymentGetToken()
        }
    }

    override fun bindViews() {
        binding.basketRecyclerView.adapter = basketAdapter
        binding.basketRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun observeData() {
        basketViewModel.basketProductListLiveData.observe(viewLifecycleOwner) {
            basketAdapter.submitList(it)
        }

        basketViewModel.costLiveData.observe(viewLifecycleOwner) {
            binding.priceSumText.text = "$it 원"
        }

        basketViewModel.productCountLiveData.observe(viewLifecycleOwner) {
            binding.productCountText.text = "($it items)"
        }
    }

}