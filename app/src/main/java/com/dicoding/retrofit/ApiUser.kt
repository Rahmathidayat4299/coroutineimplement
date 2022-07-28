package com.dicoding.retrofit

import com.dicoding.model.remote.ItemResult
import com.dicoding.model.remote.ModelDet
import com.dicoding.model.remote.UserResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiUser {
    @GET("search/users")
    suspend fun getListUser(
        @Query("q") query: String
    ): Response<UserResult>

    @GET("users/{username}")
    suspend fun userDetail(
        @Path("username") username: String
    ): Response<ModelDet>


    @GET("users/{username}/{type}")
    suspend fun pathFollow(
        @Path("username") username: String, @Path("type") type: String,
    ): Response<ArrayList<ItemResult>>
}