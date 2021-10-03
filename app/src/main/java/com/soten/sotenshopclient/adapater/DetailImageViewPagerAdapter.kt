package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soten.sotenshopclient.adapater.ProductAdapter.Companion.SPLIT_DELIMITER
import com.soten.sotenshopclient.databinding.ItemViewpagerImageBinding

class DetailImageViewPagerAdapter :
    RecyclerView.Adapter<DetailImageViewPagerAdapter.DetailImageViewHolder>() {

    private var bannerItemList = emptyList<ItemImage<String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImageViewHolder {
        return DetailImageViewHolder(
            ItemViewpagerImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setImage(list: List<ItemImage<String>>) {
        bannerItemList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DetailImageViewHolder, position: Int) {
        holder.bind(bannerItemList[position])
    }

    override fun getItemCount() = bannerItemList.size

    class DetailImageViewHolder(private val binding: ItemViewpagerImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(detailImage: ItemImage<String>) {
            val targetHeight = binding.root.resources.displayMetrics.widthPixels
            binding.image.layoutParams = binding.image.layoutParams.apply {
                height = targetHeight
            }
            binding.image.load(SPLIT_DELIMITER + detailImage)
        }
    }
}
