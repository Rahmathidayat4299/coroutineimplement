package com.dicoding.githubseconds

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.fragment.AdapterUserGithub
import com.dicoding.githubseconds.databinding.ActivityFavoriteUserBinding
import com.dicoding.model.localstorage.FavoriteUser
import com.dicoding.model.remote.ItemResult
import com.dicoding.viewmodel.FavUserVm

class FavUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapterUser: AdapterUserGithub
    private val viewModel by viewModels<FavUserVm>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterUser = AdapterUserGithub()
        adapterUser.setOnItemClickCallback(object : AdapterUserGithub.OnItemClickCallback {
            override fun onItemClik(data: ItemResult) {
                Intent(this@FavUserActivity, DetailUser::class.java).also {
                    it.putExtra(DetailUser.USERNAME_GITHUB, data.login)
                    it.putExtra(DetailUser.EXTRA_ID, data.id)
                    it.putExtra(DetailUser.EXTRA_URL_AVATAR, data.avatarUrl)
                    startActivity(it)
                }
            }

        })
        binding.rcvFavUser.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FavUserActivity)
            adapter = adapterUser
        }
        viewModel.getFavoriteUser()?.observe(this) {
            if (it != null) {
                val list = mapList(it)
                adapterUser.differ.submitList(mapList(it))
            }
        }
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<ItemResult> {
        val listUsers = ArrayList<ItemResult>()
        for (user in users) {
            val userMapped = ItemResult(
                user.html_url,
                user.login,
                user.id,
                user.avatar_url,
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}