package com.example.kotlinpro.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinpro.common.base.BaseComparator
import com.example.kotlinpro.data.network.dtos.CharacterDto
import com.example.kotlinpro.databinding.ItemCharacterBinding

class CharacterAdapter(private val onItemClick: (name: String, id: Int) -> Unit,
                       private val onLongClick: (image: String) ->Unit) :
    PagingDataAdapter<CharacterDto, CharacterAdapter.CharacterViewHolder>(
        BaseComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener{
                getItem(bindingAdapterPosition)?.let {
                    onItemClick(it.name, it.id)
                }
            }
            binding.root.setOnLongClickListener{
                getItem(absoluteAdapterPosition)?.let {
                    onLongClick(it.image)
                }
                return@setOnLongClickListener false
            }
        }

        fun onBind(item: CharacterDto) = with(binding) {
            itemCharacterName.text = item.name
            Glide.with(itemCharacterIv)
                .load(item.image)
                .into(itemCharacterIv)
        }
    }
}