package com.dicoding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.githubseconds.databinding.FragmentDetailBinding
import com.dicoding.model.remote.ItemResult
import com.dicoding.viewmodel.DetailUserVm


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailUserVm>()
    private var username = ""
    private var data: ItemResult? = null
    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailUser = args.result


        viewModel.getDetUser(username).observe(viewLifecycleOwner) {
            binding.apply {
                detailNama.text = detailUser.name
                detailUsername.text = detailUser.login
                followers.text = detailUser.followers.toString()
                following.text = detailUser.following.toString()
                following.text = detailUser.following.toString()
                repoUser.text = detailUser.publicRepos.toString()
                locationUser.text = detailUser.location
                companyUser.text = detailUser.company
                Glide.with(this@DetailFragment).load(detailUser.avatarUrl)
                    .apply(RequestOptions.circleCropTransform()).into(ivAvatar)
            }

        }
    }


}