package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.data.response.shopping.product.ProductResponse
import com.soten.sotenshopclient.databinding.ItemProductBinding

class ProductPagedListAdapter (val itemClickListener: (Int) -> Unit):
    PagingDataAdapter<ProductResponse, ProductPagedListAdapter.ProductPagedViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: ProductPagedViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductPagedViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class ProductPagedViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productResponse: ProductResponse) {
            val thumbnail = productResponse.images[0]

            binding.productImage.load(thumbnail) {
                placeholder(R.drawable.ic_soten_shop)
                RoundedCornersTransformation(50f)
                Scale.FIT
                error(R.drawable.category_banner)
            }
            binding.productTitle.text = productResponse.name
            binding.productPrice.text = productResponse.price.toString()

            binding.root.setOnClickListener {
                itemClickListener(productResponse.id)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ProductResponse>() {
            override fun areItemsTheSame(
                oldItem: ProductResponse,
                newItem: ProductResponse,
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ProductResponse,
                newItem: ProductResponse,
            ) = oldItem == newItem
        }
    }

}
