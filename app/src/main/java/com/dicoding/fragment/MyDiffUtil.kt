package com.dicoding.fragment


import androidx.recyclerview.widget.DiffUtil
import com.dicoding.model.remote.ItemResult

class MyDiffUtil(
    private val oldList: ArrayList<ItemResult>,
    private val newList: ArrayList<ItemResult>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].login != newList[newItemPosition].login -> {
                false
            }
            oldList[oldItemPosition].avatarUrl != newList[newItemPosition].avatarUrl -> {
                false
            }
            oldList[oldItemPosition].htmlUrl != newList[newItemPosition].htmlUrl -> {
                false
            }
            else -> true
        }
    }
}