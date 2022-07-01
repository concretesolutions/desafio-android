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
import com.example.desafioandroid.ui.viewmodel.MainViewModelFactory

class RepoFragment : Fragment() {

    private lateinit var binding: FragmentRepoListBinding
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = { MainViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)

        viewModel.onCreate()

        viewModel.repositoriesModel.observe(viewLifecycleOwner) {
            Log.i("repositoriesModel",it.toString())
            if (it != null) {
                val adapter =
                    RepoRecyclerViewAdapter(it,
                        object : RepoRecyclerViewAdapter.RepoSelectionListener {
                            override fun select(repoName: String, owner: String) {
                                Log.i("RepoFragment", "$repoName  $owner")
                                val action = RepoFragmentDirections.actionRepoFragmentToPullFragment(repoName,owner)
                                findNavController().navigate(action)
                            }
                        })
                binding.rvRepositories.layoutManager = LinearLayoutManager(context)

                binding.rvRepositories.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){it
            binding.bprogress.isVisible = it
        }
        return binding.root
    }
}
