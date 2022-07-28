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

@DelicateCoroutinesApi
class ListUserVm : ViewModel() {
    private val list = MutableLiveData<ArrayList<ItemResult>>()


    fun getUser(q: String): LiveData<ArrayList<ItemResult>> {

        GlobalScope.launch(Dispatchers.IO) {
            val api = RetroService.apiInstansiasi.getListUser(q)
            try {
                if (api.isSuccessful) {
                    list.postValue(api.body()?.items)
                }
            } catch (e: Exception) {
                Log.e(TAG, "getUser: $e error")
            }
        }
        return list
    }
}