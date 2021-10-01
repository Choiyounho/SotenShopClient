package com.soten.sotenshopclient.ui.setting.productregister

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charlezz.pickle.Config
import com.charlezz.pickle.Pickle
import com.charlezz.pickle.data.entity.Media
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.ShoppingApplication.Companion.appContext
import com.soten.sotenshopclient.adapater.PhotoListAdapter
import com.soten.sotenshopclient.data.request.product.ProductRegistrationRequest
import com.soten.sotenshopclient.databinding.FragmentProductRegisterBinding
import com.soten.sotenshopclient.databinding.ItemPhotoBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import com.soten.sotenshopclient.util.PathUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class ProductRegisterFragment : BaseFragment<FragmentProductRegisterBinding>() {

    override var _binding: FragmentProductRegisterBinding? = null
    private val binding get() = _binding!!

    override fun getDataBinding() = FragmentProductRegisterBinding.inflate(layoutInflater)

    private val viewModel by viewModels<ProductRegisterViewModel>()

    private val photoListAdapter by lazy { PhotoListAdapter() }

    private var imageList = mutableListOf<Media>()

    private val pickleLauncher = Pickle.register(this, object : Pickle.Callback {
        override fun onResult(result: ArrayList<Media>) {
            imageList.addAll(result)
            photoListAdapter.setImages(imageList)
        }
    })

    override fun initViews() = with(binding) {
        addImageButton.setOnClickListener {
            pickleLauncher.launch(Config.getDefault().apply {
                this.debugMode = false
            })
        }

        productAdd.setOnClickListener {
            (viewModel.getUserId() != Int.MIN_VALUE).run {

            }
            val productRegistrationRequest = ProductRegistrationRequest(
                name = nameText.text.toString(),
                description = descriptionText.text.toString(),
                categoryId = categoryIdText.text.toString().toInt(),
                price = priceText.text.toString().toInt(),
                userId = viewModel.getUserId()
            )
            lifecycleScope.launch {
                viewModel.registerProduct(productRegistrationRequest, imageList)
            }
        }
    }

    override fun bindViews() {
        binding.photoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = photoListAdapter
        }
    }


}