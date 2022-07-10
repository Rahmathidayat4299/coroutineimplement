package com.dicoding.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.githubseconds.databinding.ItemListuserBinding
import com.dicoding.model.remote.ItemResult

class AdapterUserGithub : RecyclerView.Adapter<AdapterUserGithub.ListViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<ItemResult>() {
        override fun areItemsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)


    inner class ListViewHolder( val binding: ItemListuserBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemListuserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listGithub = differ.currentList[position]

        holder.binding.apply {
            Glide.with(holder.itemView).load(listGithub.avatarUrl)
                .apply(RequestOptions.circleCropTransform()).into(imageUser)
            name.text = listGithub.login
            username.text = listGithub.id.toString()
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}