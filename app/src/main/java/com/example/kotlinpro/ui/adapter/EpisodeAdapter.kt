package com.example.kotlinpro.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpro.common.base.BaseComparator
import com.example.kotlinpro.data.network.dtos.EpisodeDto
import com.example.kotlinpro.databinding.ItemEpisodeBinding

class EpisodeAdapter(private val onItemClick: (id: Int) -> Unit) :
    PagingDataAdapter<EpisodeDto, EpisodeAdapter.EpisodeViewHolder>(
        BaseComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class EpisodeViewHolder(
        private val binding: ItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(item: EpisodeDto) = with(binding) {
            itemEpisodeId.text = item.id.toString()
            itemEpisodeName.text = item.name
            itemEpisodeAirDate.text = item.air_date
            itemEpisodeEpisode.text = item.episode
        }
    }
}