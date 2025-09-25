package com.example.digimonpedia.core.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digimonpedia.core.domain.model.Digimon
import com.example.digimonpedia.core.databinding.ItemListDigimonBinding

class DigimonAdapter: ListAdapter<Digimon, DigimonAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Digimon) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListDigimonBinding.inflate(
                android.view.LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
    inner class ListViewHolder(private var binding: ItemListDigimonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Digimon) {
            Glide.with(itemView.context)
                .load(data.img)
                .centerCrop()
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.name
            binding.tvItemSubtitle.text = data.level
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Digimon> =
            object : DiffUtil.ItemCallback<Digimon>() {
                override fun areItemsTheSame(oldItem: Digimon, newItem: Digimon): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(oldItem: Digimon, newItem: Digimon): Boolean {
                    return oldItem == newItem
                }
            }
    }
}