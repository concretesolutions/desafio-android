package com.example.desafioandroid.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        viewModel.loadRepositories("q",10)

        val recycler = binding.rvRepositories
        recycler.layoutManager = LinearLayoutManager(context)

        val adapter = RepoAdapter()

        adapter.setOnItemClickListener {
           Log.i("Test",it.toString())
            val action = RepoFragmentDirections.actionRepoFragmentToPullFragment(it.nameRepo,it.owner_repos.loginOwner)
            findNavController().navigate(action)
      //      Log.i("onRepoOwner_repo",it.name_repos)
      //      Log.i("onRepoOwner_dueño",it.owner_repos.login_owner)
      //      viewModel.onRepoOwner(it.owner_repos.login_owner,it.name_repos)
        }

        recycler.adapter = adapter

        viewModel.repositoriesModel.observe(viewLifecycleOwner) { repoList ->
            adapter.submitList(repoList)
            adapter.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(viewLifecycleOwner){it
            binding.bprogress.isVisible = it
        }
        return binding.root
    }
}
