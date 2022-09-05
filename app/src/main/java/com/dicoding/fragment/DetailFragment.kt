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
import com.dicoding.viewmodel.DetailUserVm


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailUserVm>()
    private var username = ""
    private val args: DetailFragmentArgs by navArgs()


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

        val detUser = args.result



        viewModel.getDetUser(username).observe(viewLifecycleOwner) {
            binding.apply {
                detailNama.text = detUser.name
                detailUsername.text = detUser.login
                followers.text = detUser.followers.toString()
                following.text = detUser.following.toString()
                repoUser.text = detUser.publicRepos.toString()
                locationUser.text = detUser.location
                companyUser.text = detUser.company
                Glide.with(this@DetailFragment).load(detUser.avatarUrl)
                    .apply(RequestOptions.circleCropTransform()).into(ivAvatar)
            }
        }


    }


}