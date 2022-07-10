package com.dicoding.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dicoding.fragment.util.Repository
import com.dicoding.fragment.util.Resource
import com.dicoding.model.remote.UserResult
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewmodel(val repositry: Repository) : ViewModel() {
    val list: MutableLiveData<Resource<UserResult>> = MutableLiveData()
    var listSearch: UserResult? = null
    fun searchNews(query: String) = viewModelScope.launch {
        list.postValue(Resource.Loading())
        val response = repositry.searchUser(query)
        list.postValue(getSearchUser(response))
    }

    private fun getSearchUser(response: Response<UserResult>): Resource<UserResult> {
        if (response.isSuccessful) {
            response.body()?.let { userResult ->
                if (listSearch == null) {
                    listSearch == userResult
                } else {
                    val oldArticles = listSearch?.items
                    val newArticles = userResult.items
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(listSearch ?: userResult)
            }
        }
        return Resource.Error(response.message())
    }
}

//class SearchVmFactory(val repositry: Repository): ViewModelProvider.Factory{
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(SearchViewmodel::class.java)){
//            return SearchViewmodel(repositry) as T
//        }
//        throw IllegalArgumentException("Unknown view model class")
//    }
//}