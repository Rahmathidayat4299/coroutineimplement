package com.dicoding.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.model.remote.ItemResult
import com.dicoding.retrofit.RetroService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

@DelicateCoroutinesApi
class FollowingVm : ViewModel() {
    private val dataFollowing = MutableLiveData<ArrayList<ItemResult>>()

    fun getFollowing(username: String): LiveData<ArrayList<ItemResult>> {
        GlobalScope.launch(Dispatchers.IO) {
            val response =
                RetroService.apiInstansiasi.pathFollow(username, "following")
            try {
                if (response.isSuccessful) {
                    dataFollowing.postValue(response.body())
                }
            } catch (e: Exception) {
                Log.e(TAG, "getFollowing: $e message list following error")
            }
        }

        return dataFollowing
    }
}