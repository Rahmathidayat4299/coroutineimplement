package com.dicoding.util

import com.dicoding.retrofit.RetroService

class Repository {
    suspend fun getListFollower(username: String) =
        RetroService.apiInstansiasi.pathFollow(username, "followers")
}