package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.databinding.ItemProductBinding
import com.soten.sotenshopclient.domain.response.ProductResponse

class ProductAdapter :
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
            binding.productImage.load(R.drawable.ic_soten_shop)
            binding.productTitle.text = productResponse.name
            binding.productPrice.text = productResponse.price.toString()
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