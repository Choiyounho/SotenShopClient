package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soten.sotenshopclient.data.db.entity.LikedEntity
import com.soten.sotenshopclient.databinding.ItemLikedProductBinding

class ProductLikedEntityAdapter(
    val itemClickListener: (Int) -> Unit,
    val deleteClickListener: (LikedEntity) -> Unit,
) :
    ListAdapter<LikedEntity, ProductLikedEntityAdapter.ProductViewHolder>(differ) {

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemLikedProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ProductViewHolder(private val binding: ItemLikedProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: LikedEntity) = with(binding) {
            likedEntity = product

            thumbnailImage.load(product.product.thumbnailImage)

            thumbnailImage.setOnClickListener { itemClickListener(product.id) }
            deleteImage.setOnClickListener { deleteClickListener(product) }
        }

    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<LikedEntity>() {
            override fun areItemsTheSame(
                oldItem: LikedEntity,
                newItem: LikedEntity,
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: LikedEntity,
                newItem: LikedEntity,
            ) = oldItem == newItem
        }
    }

}