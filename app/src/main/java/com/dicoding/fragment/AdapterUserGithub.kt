package com.dicoding.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.githubseconds.DetailUser
import com.dicoding.model.remote.ItemResult
import com.dicoding.model.remote.ModelDet
import com.dicoding.githubseconds.databinding.ItemListuserBinding


class AdapterUserGithub : RecyclerView.Adapter<AdapterUserGithub.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private var listUser = ArrayList<ItemResult>()
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private var onClickListener: OnClickListener? = null

    private val differCallback = object : DiffUtil.ItemCallback<ItemResult>() {
        override fun areItemsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemResult, newItem: ItemResult): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)


    inner class ListViewHolder(val binding: ItemListuserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(users: ItemResult) {
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

        holder.bind(differ.currentList[position])
    }


    override fun getItemCount(): Int = differ.currentList.size

    interface OnClickListener {
        fun onClick(item: ModelDet)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnItemClickCallback {
        fun onItemClik(data: ItemResult)
    }

    fun setData(listUserGithub: ArrayList<ItemResult>) {


    }

}