package com.dicoding.fragment


import androidx.recyclerview.widget.DiffUtil
import com.dicoding.model.remote.ItemResult

class MyDiffUtil : DiffUtil.ItemCallback<ItemResult>() {
    override fun areItemsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
        return oldItem.id == newItem.id
    }
}