package com.dicoding.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.model.remote.ItemResult
import com.dicoding.retrofit.RetroService
import com.dicoding.util.Repository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ListUserVm : ViewModel() {
    private val list = MutableLiveData<ArrayList<ItemResult>>()


    fun getUser(q: String): LiveData<ArrayList<ItemResult>> {
        viewModelScope.launch {
            val  listUser = RetroService.apiInstansiasi.getListUser(q)
            try {
                if (listUser.isSuccessful){
                    list.postValue(listUser.body()?.items)
                }
            }catch (e:Exception){
                Log.e(TAG, "getUser: $e error")
            }
        }
        return list
    }
}