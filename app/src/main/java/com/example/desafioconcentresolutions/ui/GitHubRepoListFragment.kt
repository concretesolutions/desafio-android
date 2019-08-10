package com.example.desafioconcentresolutions.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.desafioconcentresolutions.R
import com.example.desafioconcentresolutions.adapters.GitHubRepoAdapter
import com.example.desafioconcentresolutions.models.Resource
import com.example.desafioconcentresolutions.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_git_hub_repo_list.*


class GitHubRepoListFragment : Fragment() {

    private lateinit var mMainViewModel:MainViewModel

    private lateinit var mGitRepoAdapter : GitHubRepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_git_hub_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            mMainViewModel = MainViewModel(it.application)
        }
        mMainViewModel.loadFirstPage()
        setupObservable()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        context?.let {
            mGitRepoAdapter = GitHubRepoAdapter(it)
            recyclerView.adapter = mGitRepoAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
        }

    }

    private fun setupObservable() {
        mMainViewModel.getGitHubRepoList().observe(viewLifecycleOwner, Observer {resource->
            when(resource.status){
                Resource.Status.SUCCESS -> {
                    resource.data?.let{
                        mGitRepoAdapter.updateGitHubList(it)
                    }
                }
                Resource.Status.ERROR -> mGitRepoAdapter.setStatus(GitHubRepoAdapter.GitHubRepoStatus.ERROR_GITHUB_REPO)
                Resource.Status.LOADING -> mGitRepoAdapter.setStatus(GitHubRepoAdapter.GitHubRepoStatus.LOADING_GITHUB_REPO)
            }
        })
    }
}
