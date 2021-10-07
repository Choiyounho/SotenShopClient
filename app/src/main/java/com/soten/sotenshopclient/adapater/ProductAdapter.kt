package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.data.response.product.ProductResponse
import com.soten.sotenshopclient.databinding.ItemProductBinding

class ProductAdapter(
    val itemClickListener: (Int) -> Unit,
) :
    ListAdapter<ProductResponse, ProductAdapter.ProductViewHolder>(differ) {

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productResponse: ProductResponse) {
            val thumbnail = productResponse.images.first()

            binding.productImage.load(thumbnail) {
                placeholder(R.drawable.ic_soten_shop)
                RoundedCornersTransformation(50f)
                Scale.FIT
                error(R.drawable.ic_error)
            }
            binding.productTitle.text = productResponse.name
            binding.productPrice.text = productResponse.price.toString()

            binding.root.setOnClickListener {
                itemClickListener(productResponse.id)
            }
        }

    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<ProductResponse>() {
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