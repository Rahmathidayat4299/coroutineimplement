package com.dicoding.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.model.localstorage.FavoriteUser
import com.dicoding.model.localstorage.FavoriteUserDao
import com.dicoding.model.localstorage.UserDatabase
import com.dicoding.model.remote.ModelDet
import com.dicoding.retrofit.RetroService
import com.dicoding.util.Repository
import kotlinx.coroutines.*


class DetailUserVm(application: Application) : AndroidViewModel(application) {

    private val detailUser = MutableLiveData<ModelDet>()
    private var userDao: FavoriteUserDao? = null
    private var userDb: UserDatabase? = UserDatabase.getDatabase(application)

    init {
        userDao = userDb?.favoriteUserDao()

    }

    fun getDetUser(username: String): LiveData<ModelDet> {

        viewModelScope.launch {
            val response = RetroService.apiInstansiasi.userDetail(username)
            try {
                if (response.isSuccessful) {
                    detailUser.postValue(response.body())
                }
            } catch (e: Exception) {
                Log.e(TAG, "getDetUser: $e error detailuser not found")
            }
        }
        return detailUser
    }


//    fun getDetUser(username: String): LiveData<ModelDet> {
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = RetroService.apiInstansiasi.userDetail(username)
//            try {
//                if (response.isSuccessful) {
//                    detailUser.postValue(response.body())
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "getDetUser: $e error detailuser not found")
//            }
//        }
//
//        return detailUser
//    }


    fun addFavorite(username: String?, id: Int, avatarUrl: String?, url: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteUser(username, id, avatarUrl, url)
            userDao?.addToFavorite(user)
        }
    }

    fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavorite(id)
        }
    }
}