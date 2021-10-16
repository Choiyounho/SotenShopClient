package com.soten.sotenshopclient.ui.setting.productregister

import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charlezz.pickle.Config
import com.charlezz.pickle.Pickle
import com.charlezz.pickle.data.entity.Media
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.adapater.PhotoListAdapter
import com.soten.sotenshopclient.data.request.shopping.product.ProductRegistrationRequest
import com.soten.sotenshopclient.databinding.FragmentProductRegisterBinding
import com.soten.sotenshopclient.databinding.PopUpCategoryBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductRegisterFragment : BaseFragment<FragmentProductRegisterBinding>() {

    override var _binding: FragmentProductRegisterBinding? = null
    private val binding get() = _binding!!

    override fun getDataBinding() = FragmentProductRegisterBinding.inflate(layoutInflater)

    private val viewModel by viewModels<ProductRegisterViewModel>()

    private val photoListAdapter by lazy { PhotoListAdapter() }

    private var imageList = mutableListOf<Media>()

    private lateinit var popUpWindow: PopupWindow

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
                categoryId = viewModel.getCategory(),
                price = priceText.text.toString().toInt(),
                userId = viewModel.getUserId()
            )

            lifecycleScope.launch {
                viewModel.registerProduct(productRegistrationRequest, imageList)
            }
        }

        categoryIdText.setOnClickListener {
            val popUpBinding = PopUpCategoryBinding.inflate(layoutInflater)

            popUpBinding.viewModel = viewModel

            popUpWindow = PopupWindow(popUpBinding.root,
                categoryIdText.width,
                ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                elevation = 8f
                isOutsideTouchable = true
                showAsDropDown(categoryIdText)
            }
        }
    }

    override fun bindViews() {
        binding.photoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = photoListAdapter
        }
    }

    override fun observeData() {
        val dialog = LoadingDialogFragment()

        viewModel.productRegisterState.observe(viewLifecycleOwner) { state ->
            when (state) {
                ProductRegisterState.NORMAL -> Unit
                ProductRegisterState.LOADING -> showLoadingDialogFragment(dialog)
                ProductRegisterState.SUCCESS -> registerSuccess(dialog)
                ProductRegisterState.FAIL -> registerFail(dialog)
                ProductRegisterState.NOT_SELECT -> nonSelect(dialog)
                else -> Unit
            }
        }

        viewModel.categoryLiveData.observe(viewLifecycleOwner) {
            binding.categoryIdText.text = it.title
            popUpWindow.dismiss()
        }
    }

    private fun nonSelect(dialog: LoadingDialogFragment) {
        dialog.dismiss()
        Toast.makeText(context, "카테고리를 선택해주세요", Toast.LENGTH_SHORT).show()
    }

    private fun showLoadingDialogFragment(dialog: LoadingDialogFragment) {
        dialog.show(parentFragmentManager, "")
    }

    private fun registerSuccess(dialog: LoadingDialogFragment) {
        dialog.dismiss()
        Toast.makeText(context, "상품 등록 완료!!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.registerAfterToHomeFragment)
    }

    private fun registerFail(dialog: LoadingDialogFragment) {
        dialog.dismiss()
        Toast.makeText(context, "상품 등록 실패!!", Toast.LENGTH_SHORT).show()
    }

}