package com.concrete.challenge.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.R
import com.concrete.challenge.data.UserEntity
import com.concrete.challenge.ui.adapters.RepositoryAdapter
import com.concrete.challenge.domain.io.response.RepositoriesResponse
import com.concrete.challenge.presentation.model.RepositoryItem
import com.concrete.challenge.presentation.toRepositoryItem
import com.concrete.challenge.presentation.viewmodel.RepositoryViewModel
import com.concrete.challenge.presentation.viewmodel.UserViewModel
import com.concrete.challenge.utils.Constants.TAG
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment : Fragment() {

    private val adapter by lazy { RepositoryAdapter(manager = RepositoryManager()) }
    private val repositoryViewModel: RepositoryViewModel by viewModel()
    private val userViewModel: UserViewModel by viewModel()

    private lateinit var rvRepository: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_repositories, container, false)
        rvRepository = view.findViewById(R.id.rvRepository)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvRepository.layoutManager = LinearLayoutManager(requireContext())

        val dividerItemDecoration = DividerItemDecoration(requireContext(), (rvRepository.layoutManager as LinearLayoutManager).orientation)
        rvRepository.addItemDecoration(dividerItemDecoration)

        initView()
    }

    private fun initView() {
        initRecyclerView()
        initObservers()
        loadInfo()
    }

    private fun initRecyclerView() {
        rvRepository.adapter = adapter
    }

    private fun initObservers() {
        Log.i(TAG, "initObservers()")
        repositoryViewModel.repositoriesResponse.observe(viewLifecycleOwner, ::addRepositories)
        //userViewModel.userResponse.observe(viewLifecycleOwner, ::addUser)
    }

    private fun loadInfo() {
        repositoryViewModel.getRepositories()
        userViewModel.getUser()
    }

    private fun addRepositories(repositoriesResponse: RepositoriesResponse?) {

        val viewFlipper = view?.findViewById<ViewFlipper>(R.id.viewFlipper)

        if (repositoriesResponse != null) {
            val item = repositoriesResponse.repositoriesEntityList.map {
                    repository -> repository.toRepositoryItem()
            }

            viewFlipper?.showNext()
            adapter.setItems(item)
        }
    }

    private fun addUser(userResponse: List<UserEntity>) {
        // TODO
    }

    inner class RepositoryManager : RepositoryAdapter.AdapterManager {
        override fun onRepositoryClicked(repositoryClicked: RepositoryItem) {

            val bundle = Bundle()

            bundle.putString("username", repositoryClicked.repositoryOwner.username)
            bundle.putString("userName", repositoryClicked.repositoryOwner.userName)
            bundle.putString("openMR", repositoryClicked.openPullRequestAmount.toString())
            bundle.putString("closedMR", repositoryClicked.closedPullRequestAmount.toString())
            bundle.putString("url", repositoryClicked.pullRequestsUrl)

            parentFragmentManager.setFragmentResult("key", bundle)

            findNavController().navigate(
                R.id.action_repositoriesFragment_to_pullRequestFragment
            )
        }
    }
}
