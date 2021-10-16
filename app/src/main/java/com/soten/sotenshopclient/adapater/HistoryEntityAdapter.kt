package com.soten.sotenshopclient.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soten.sotenshopclient.data.db.entity.HistoryEntity
import com.soten.sotenshopclient.databinding.ItemSearchHistoryBinding

class HistoryEntityAdapter(
    val itemClickListener: (HistoryEntity) -> Unit,
    val deleteClickListener: (HistoryEntity) -> Unit,
) :
    ListAdapter<HistoryEntity, HistoryEntityAdapter.HistoryViewHolder>(differ) {

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            ItemSearchHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class HistoryViewHolder(private val binding: ItemSearchHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(history: HistoryEntity) = with(binding) {
            keywordText.text = history.keyword
            keywordText.setOnClickListener { itemClickListener(history) }

            deleteImage.setOnClickListener { deleteClickListener(history) }
        }

    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<HistoryEntity>() {
            override fun areItemsTheSame(
                oldItem: HistoryEntity,
                newItem: HistoryEntity,
            ) = oldItem.keyword == newItem.keyword

            override fun areContentsTheSame(
                oldItem: HistoryEntity,
                newItem: HistoryEntity,
            ) = oldItem == newItem
        }
    }

}