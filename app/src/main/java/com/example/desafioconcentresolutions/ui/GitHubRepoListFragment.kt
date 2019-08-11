package com.example.desafioconcentresolutions.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.desafioconcentresolutions.R
import com.example.desafioconcentresolutions.adapters.GitHubRepoAdapter
import com.example.desafioconcentresolutions.models.Operation
import com.example.desafioconcentresolutions.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_git_hub_repo_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class GitHubRepoListFragment : Fragment(), GitHubRepoAdapter.GitHubRepoListener {

    private val mMainViewModel by sharedViewModel<MainViewModel>()

    private lateinit var mGitRepoAdapter : GitHubRepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_git_hub_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLoadingState()
        setupObservable()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        context?.let {
            mGitRepoAdapter = GitHubRepoAdapter(it)
            recyclerView.adapter = mGitRepoAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)

            mGitRepoAdapter.setGitHubRepoListener(this)
        }

    }

    private fun setupObservable() {
        mMainViewModel.getGitHubRepoList().observe(viewLifecycleOwner, Observer {resource->
            mGitRepoAdapter.submitList(resource)
        })

        mMainViewModel.getGitRepoLoadingOperation().observe(viewLifecycleOwner, Observer {
            when(it){
                Operation.LOADING -> setLoadingState()
                Operation.SUCESS -> setDefaultState()
                else-> setLoadingState()
            }
        })

    }

    override fun onRepoClicked(ownerName: String, repo: String) {
        mMainViewModel.loadPullForUser(ownerName, repo)
        NavHostFragment.findNavController(this@GitHubRepoListFragment)
            .navigate(R.id.action_gitHubRepoListFragment_to_gitHubPullListFragment)
    }

    private fun setLoadingState(){
        container_gitHubRepoListFragment_loading.visibility = View.VISIBLE
        container_gitHubRepoListFragment_default.visibility = View.GONE
    }

    private fun setDefaultState(){
        container_gitHubRepoListFragment_loading.visibility = View.GONE
        container_gitHubRepoListFragment_default.visibility = View.VISIBLE
    }
}
