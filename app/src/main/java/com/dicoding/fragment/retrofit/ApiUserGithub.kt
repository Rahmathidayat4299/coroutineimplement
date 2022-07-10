package com.dicoding.fragment.retrofit

import com.dicoding.model.remote.UserResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUserGithub {
    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String): Response<UserResult>
}