package com.dicoding.util

import com.dicoding.retrofit.RetroService

class Repository {
    suspend fun listUser(q: String) = RetroService.apiInstansiasi.getListUser(q)
    suspend fun detailUser(username: String) = RetroService.apiInstansiasi.userDetail(username)
    suspend fun getListFollower(username: String) =
        RetroService.apiInstansiasi.pathFollow(username, "followers")

    suspend fun getListFollowing(username: String) =
        RetroService.apiInstansiasi.pathFollow(username, "following")
}