package com.dicoding.githubseconds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dicoding.fragment.util.Repository
import com.dicoding.fragment.viewmodel.SearchViewmodel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: SearchViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val searchRepository = Repository()
//        val viewModelProviderFactory = GithubVmFactory(searchRepository)
//        viewModel =
//            ViewModelProvider(this, viewModelProviderFactory).get(SearchViewmodel::class.java)
    }
}