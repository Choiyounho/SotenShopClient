package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soten.sotenshopclient.data.db.entity.BasketEntity
import com.soten.sotenshopclient.databinding.ItemBasketProductBinding

class ProductBasketEntityAdapter(
    val itemClickListener: (Int) -> Unit,
    val deleteClickListener: (BasketEntity) -> Unit,
    val plusClickListener: (BasketEntity) -> Unit,
    val minusClickListener: (BasketEntity) -> Unit,
) :
    ListAdapter<BasketEntity, ProductBasketEntityAdapter.ProductViewHolder>(differ) {

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemBasketProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ProductViewHolder(private val binding: ItemBasketProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: BasketEntity) {
            binding.basketEntity = product

            binding.thumbnailImage.load(product.product.thumbnailImage)

            binding.thumbnailImage.setOnClickListener { itemClickListener(product.id) }
            binding.deleteImage.setOnClickListener { deleteClickListener(product) }
            binding.plusButton.setOnClickListener { plusClickListener(product) }
            binding.minusButton.setOnClickListener { minusClickListener(product) }
        }

    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<BasketEntity>() {
            override fun areItemsTheSame(
                oldItem: BasketEntity,
                newItem: BasketEntity,
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: BasketEntity,
                newItem: BasketEntity,
            ) = oldItem == newItem
        }
    }

}