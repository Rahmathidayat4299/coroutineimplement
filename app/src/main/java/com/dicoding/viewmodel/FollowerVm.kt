package com.dicoding.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.model.remote.ItemResult
import com.dicoding.retrofit.RetroService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


@DelicateCoroutinesApi
class FollowerVm : ViewModel() {
    private val dataFollower = MutableLiveData<ArrayList<ItemResult>>()

    fun getFollower(username: String): LiveData<ArrayList<ItemResult>> {
        viewModelScope.launch {
            val listFollower =
                RetroService.apiInstansiasi.pathFollow(username, "followers")
            try {
                if (listFollower.isSuccessful) {
                    dataFollower.postValue(listFollower.body())
                }
            } catch (e: Exception) {
                Log.e(TAG, "getFollower: $e message listfollower")
            }
        }
        return dataFollower
    }
}


