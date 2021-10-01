package com.concrete.challenge.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.concrete.challenge.R
import com.concrete.challenge.ui.adapters.RepositoryAdapter
import com.concrete.challenge.databinding.FragmentRepositoriesBinding
import com.concrete.challenge.domain.io.response.RepositoriesResponse
import com.concrete.challenge.presentation.model.RepositoryItem
import com.concrete.challenge.presentation.toRepositoryItem
import com.concrete.challenge.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment : Fragment() {

    private val adapter by lazy { RepositoryAdapter(manager = RepositoryManager()) }
    private val viewModel: RepositoryViewModel by viewModel()

    private lateinit var binding: FragmentRepositoriesBinding

    private val rvRepository by lazy { binding.rvRepository }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        rvRepository.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        initRecyclerView()
        loadList()
    }

    private fun initRecyclerView() {
        rvRepository.adapter = adapter
    }

    private fun loadList() {
        viewModel.repositoriesResponse.observe(viewLifecycleOwner, ::add)
        viewModel.getRepositories()
    }

    private fun add(repositoriesResponse: RepositoriesResponse) {
        val item = repositoriesResponse.repositoriesEntityList?.map {
                repository -> repository.toRepositoryItem()
        }

        item?.let { adapter.addItems(item) }
    }

    inner class RepositoryManager : RepositoryAdapter.AdapterManager {
        override fun onRepositoryClicked(repositoryClicked: RepositoryItem) {

            val bundle = Bundle()

            bundle.putString("openMR", repositoryClicked.openPullRequestAmount.toString())
            bundle.putString("closedMR", repositoryClicked.closedPullRequestAmount.toString())

            parentFragmentManager.setFragmentResult("key", bundle)

            findNavController().navigate(
                R.id.action_repositoriesFragment_to_pullRequestFragment
            )
        }
    }
}
