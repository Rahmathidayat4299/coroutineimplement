package com.dicoding.fragment.retrofit

import com.dicoding.retrofit.ApiUser
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUser {
    private const val BASE_URL = "https://api.github.com/"

    private var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Token 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")
            .build()
        chain.proceed(newRequest)
    }.build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstansiasi: ApiUserGithub = retrofit.create(ApiUserGithub::class.java)
}