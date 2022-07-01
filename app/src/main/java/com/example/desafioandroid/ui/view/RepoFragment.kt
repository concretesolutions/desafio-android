package com.example.desafioandroid.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroid.databinding.FragmentRepoListBinding
import com.example.desafioandroid.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoFragment : Fragment() {

    private lateinit var binding: FragmentRepoListBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)

        viewModel.onCreate()

        val recycler = binding.rvRepositories
        recycler.layoutManager = LinearLayoutManager(context)
        val adapter = RepoAdapter()

        adapter.setOnItemClickListener {
            Log.i("Test",it.toString())
            val action = RepoFragmentDirections.actionRepoFragmentToPullFragment(it.name_repos,it.owner_repos.login_owner)
            findNavController().navigate(action)
        }

        recycler.adapter = adapter

        viewModel.repositoriesModel.observe(viewLifecycleOwner) { repoList ->
            adapter.submitList(repoList)
        }


/*        viewModel.repositoriesModel.observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = RepoRecyclerViewAdapter(it, object : RepoRecyclerViewAdapter.RepoSelectionListener {
                override fun select(repoName: String, owner: String) {

                            }
                        })
                binding.rvRepositories.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
*/
        viewModel.isLoading.observe(viewLifecycleOwner){it
            binding.bprogress.isVisible = it
        }
        return binding.root
    }
}
