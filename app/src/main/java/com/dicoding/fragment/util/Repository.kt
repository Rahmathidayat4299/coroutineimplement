package com.dicoding.fragment.util

import com.dicoding.fragment.retrofit.RetrofitUser
import com.dicoding.retrofit.RetroService

class Repository {
    suspend fun searchUser(query: String) = RetrofitUser.apiInstansiasi.searchUser(query)
}