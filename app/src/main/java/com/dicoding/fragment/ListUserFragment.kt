package com.dicoding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubseconds.R
import com.dicoding.githubseconds.databinding.FragmentListUserBinding
import com.dicoding.model.remote.ModelDet
import com.dicoding.viewmodel.ListUserVm
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class ListUserFragment : Fragment() {
    private var _binding: FragmentListUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterUser: AdapterUserGithub
    private val viewModel by viewModels<ListUserVm>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListUserBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterUser = AdapterUserGithub()

        adapterUser.setOnClickListener(object : AdapterUserGithub.OnClickListener {
            override fun onClick(item: ModelDet) {
                val bundle = Bundle()
                bundle.putParcelable("result", item)
                findNavController().navigate(R.id.action_listUserFragment_to_detailFragment, bundle)

            }
        })





        binding.search.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query?.isNotEmpty() == true) {
                        viewLoading(true)
                        viewModel.getUser(query).observe(viewLifecycleOwner) {
                            if (it != null) {
                                adapterUser.differ.submitList(it)
                                viewLoading(false)
                                showData()
                            }
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }

    }

    private fun viewLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showData() {

        binding.rcvUser.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}