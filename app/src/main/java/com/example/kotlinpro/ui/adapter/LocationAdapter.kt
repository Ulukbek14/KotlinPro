package com.example.kotlinpro.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpro.common.base.BaseComparator
import com.example.kotlinpro.data.network.dtos.LocationDto
import com.example.kotlinpro.databinding.ItemLocationBinding

class LocationAdapter(private val onItemClick: (id: Int) -> Unit) :
    PagingDataAdapter<LocationDto, LocationAdapter.LocationViewHolder>(
        BaseComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class LocationViewHolder(
        private val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?. let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(item: LocationDto) = with(binding) {
            itemLocationId.text = item.id.toString()
            itemLocationName.text = item.name
            itemLocationType.text = item.type
            itemLocationDimension.text = item.dimension
        }
    }
}