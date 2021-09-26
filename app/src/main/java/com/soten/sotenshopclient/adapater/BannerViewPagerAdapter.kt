package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.databinding.ItemBannerBinding

class BannerViewPagerAdapter : RecyclerView.Adapter<BannerViewPagerAdapter.BannerViewHolder>() {

    private var bannerItemList = emptyList<BannerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    init {
        setImage(listOf(
            BannerItem(R.drawable.home_banner_1),
            BannerItem(R.drawable.home_banner_2),
            BannerItem(R.drawable.home_banner_3),
        ))
    }

    private fun setImage(list: List<BannerItem>) {
        bannerItemList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(bannerItemList[position])
    }

    override fun getItemCount() = bannerItemList.size

    inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bannerItem: BannerItem) {
            binding.bannerImage.load(bannerItem.image)
        }

    }
}

data class BannerItem(
    @DrawableRes val image: Int
)
