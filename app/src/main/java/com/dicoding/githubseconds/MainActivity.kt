package com.dicoding.githubseconds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.fragment.viewmodel.SearchViewmodel
import com.dicoding.githubseconds.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: SearchViewmodel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val searchRepository = Repository()
//        val viewModelProviderFactory = GithubVmFactory(searchRepository)
//        viewModel =
//            ViewModelProvider(this, viewModelProviderFactory).get(SearchViewmodel::class.java)
    }
}