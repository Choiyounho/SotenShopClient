package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.charlezz.pickle.data.entity.Media
import com.soten.sotenshopclient.databinding.ItemPhotoBinding

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    val items = ArrayList<Media>()

    fun setImages(mediaList: List<Media>) {
        items.clear()
        items.addAll(mediaList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(media: Media) {
            binding.photo.load(media.getUri())
        }
    }

}