package com.soten.sotenshopclient.ui.setting.productregister

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.databinding.FragmentLoadingDialogBinding

class LoadingDialogFragment : DialogFragment(R.layout.fragment_loading_dialog) {

    private var _binding: FragmentLoadingDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoadingDialogBinding.bind(view)

        val imageLoader = ImageLoader.Builder(binding.root.context)
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(binding.root.context))
                } else {
                    add(GifDecoder())
                }
            }.build()
        binding.loadingGif.load(R.drawable.loadingGif, imageLoader)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}