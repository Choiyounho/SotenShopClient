package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soten.sotenshopclient.databinding.ItemViewpagerImageBinding

class BannerViewPagerAdapter : RecyclerView.Adapter<BannerViewPagerAdapter.BannerViewHolder>() {

    private var bannerItemList = emptyList<ItemImage<Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            ItemViewpagerImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setImage(list: List<ItemImage<Int>>) {
        bannerItemList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(bannerItemList[position])
    }

    override fun getItemCount() = bannerItemList.size

    class BannerViewHolder(private val binding: ItemViewpagerImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemImage: ItemImage<Int>) {
            binding.image.load(itemImage.image)
        }
    }
}

data class ItemImage<T>(
    val image: T,
)
