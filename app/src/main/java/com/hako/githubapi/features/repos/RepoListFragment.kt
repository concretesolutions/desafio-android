package com.hako.githubapi.features.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hako.githubapi.R
import com.hako.githubapi.data.repository.retrofit.RetrofitDatasource
import com.hako.githubapi.domain.requests.QueryRepository
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoListFragment : Fragment() {

    private val githubApi: RetrofitDatasource by inject()
    private val viewModel: RepoListViewModel by viewModel()

    companion object {
        fun newInstance() = RepoListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repo_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        githubApi.getRepositories(QueryRepository())
        viewModel.loadRepositories()
    }
}
