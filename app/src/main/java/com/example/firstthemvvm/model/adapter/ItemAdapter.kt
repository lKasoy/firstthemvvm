package com.example.firstthemvvm.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firstthemvvm.databinding.FragmentItemBinding
import com.example.firstthemvvm.model.entity.Item

class ItemAdapter
    (private val onClick: (Item) -> Unit)
    : androidx.recyclerview.widget.ListAdapter<Item, ItemAdapter.ViewHolder> (DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = currentList[position]
        holder.bind(item, onClick)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return  oldItem.id == newItem.id
        }
    }

    class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, onItemClick: (Item) -> Unit) {
            binding.apply {
                name.text = item.name
                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}