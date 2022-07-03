package com.example.desafioandroid.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
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

        //EU por ahora esta buscando el valor hardcodeado..
        viewModel.loadRepositories("q", 10)

        val recycler = binding.rvRepositories
        val manager = LinearLayoutManager(context)
        recycler.layoutManager = manager
        recycler.addItemDecoration(DividerItemDecoration(context, manager.orientation))
        val adapter = RepoAdapter()

        adapter.setOnItemClickListener {
            Log.i("Test", it.toString())
            val action = RepoFragmentDirections.actionRepoFragmentToPullFragment(
                it.nameRepo,
                it.owner_repos.loginOwner
            )
            findNavController().navigate(action)
        }

        recycler.adapter = adapter

        viewModel.repositoriesModel.observe(viewLifecycleOwner) { repoList ->
            adapter.submitList(repoList)
            adapter.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            it
            binding.bprogress.isVisible = it
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            activity?.finish()
        }

        return binding.root
    }
}
