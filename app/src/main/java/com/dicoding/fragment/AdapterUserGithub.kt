package com.dicoding.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.adapter.AdapterUser
import com.dicoding.githubseconds.DetailUser
import com.dicoding.githubseconds.databinding.ItemListuserBinding
import com.dicoding.model.remote.ItemResult

class AdapterUserGithub : RecyclerView.Adapter<AdapterUserGithub.ListViewHolder>() {
    private var listUser = ArrayList<ItemResult>()
    private var onItemClickCallback: AdapterUser.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: AdapterUser.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(item: ArrayList<ItemResult>) {
        listUser.apply {
            clear()
            addAll(item)
            notifyDataSetChanged()
        }
    }
//    private val differCallback = object : DiffUtil.ItemCallback<ItemResult>() {
//        override fun areItemsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//    val differ = AsyncListDiffer(this, differCallback)


    inner class ListViewHolder(val binding: ItemListuserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(users: ItemResult) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClik(users)
            }
            binding.apply {
                name.text = users.login
                username.text = users.id.toString()
            }
            Glide.with(itemView.context)
                .load(users.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imageUser)
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailUser::class.java)
                intent.putExtra(DetailUser.USERNAME_GITHUB, users.login)
                itemView.context.startActivity(intent)
            }
        }
    }


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
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    fun setOnItemClickListener(listen: (ItemResult) -> Unit) {
        onItemClickListener = listen
    }

    private var onItemClickListener: ((ItemResult) -> Unit)? = null
}