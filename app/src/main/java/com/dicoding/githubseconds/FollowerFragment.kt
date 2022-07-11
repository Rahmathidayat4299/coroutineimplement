package com.dicoding.githubseconds

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.adapter.AdapterUser
import com.dicoding.fragment.AdapterUserGithub
import com.dicoding.githubseconds.databinding.FollowFragmentBinding
import com.dicoding.viewmodel.FollowerVm

class FollowerFragment : Fragment() {
    private var _binding: FollowFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FollowerVm>()
    private lateinit var adapterUser: AdapterUserGithub
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FollowFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailUser.USERNAME_GITHUB).toString()

        adapterUser = AdapterUserGithub()

        binding.rvFollow.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
        viewLoading(true)

        viewModel.getFollower(username).observe(viewLifecycleOwner) {
            if (it != null) adapterUser.setData(it)
            viewLoading(false)
        }
    }

    private fun viewLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}