package com.example.desafioandroid.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroid.data.model.RepoRecyclerViewAdapter
import com.example.desafioandroid.databinding.FragmentRepoListBinding
import com.example.desafioandroid.ui.viewmodel.RepoViewModel
import com.example.desafioandroid.ui.viewmodel.RepoViewModelFactory

class RepoFragment : Fragment() {

    private lateinit var binding: FragmentRepoListBinding
    private val viewModel: RepoViewModel by viewModels(
        factoryProducer = { RepoViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)

        binding.rvRepositories.layoutManager = LinearLayoutManager(context)

        viewModel.onCreate()

        viewModel.repositoriesModel.observe(viewLifecycleOwner) { value ->
            Log.i("repositoriesModel",value.toString())
            if (value != null) {
                val adapter =
                    RepoRecyclerViewAdapter(value,
                        object : RepoRecyclerViewAdapter.RepoSelectionListener {
                            override fun select(repoName: String) {
                                Log.i("RepoFragment", repoName)
                            }
                        })
                adapter.notifyDataSetChanged()
                binding.rvRepositories.adapter = adapter
            }
        }
        return binding.root
    }
}
