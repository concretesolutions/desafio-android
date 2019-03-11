package com.hako.githubapi.features.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hako.githubapi.R
import com.hako.githubapi.data.retrofit.NetworkStatus
import com.hako.githubapi.data.retrofit.RemoteDatasource
import com.hako.githubapi.domain.entities.Repository
import com.hako.githubapi.domain.requests.QueryRepository
import com.hako.githubapi.features.repos.adapter.RepoAdapter
import kotlinx.android.synthetic.main.repo_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RepoListFragment : Fragment() {

    private val githubApi: RemoteDatasource by inject()
    private val viewModel: RepoListViewModel by viewModel()

    companion object {
        const val PREFETCH_THRESHOLD = 30

        fun newInstance() = RepoListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.repo_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        githubApi.getRepositories(QueryRepository())
        initRepoAdapter()
        viewModel.loadRepositories()
        viewModel.networkStatus.observe(this, Observer {
            when (it) {
                NetworkStatus.Ready -> repo_refresh.isRefreshing = false
                NetworkStatus.Errored -> Toast.makeText(context, "Error cargando datos", Toast.LENGTH_LONG).show()
            }
        })
        initRefresh()
    }

    private fun initRefresh() {
        repo_refresh.setOnRefreshListener {
            viewModel.refreshRepositories()
        }
    }

    private fun initRepoAdapter() {
        val adapter = RepoAdapter {
            itemClicked(it)
        }
        repo_recycler_list.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.VERTICAL
        }
        repo_recycler_list.setHasFixedSize(true)
        repo_recycler_list.adapter = adapter
        // Prefetching data
        repo_recycler_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                (recyclerView.layoutManager as LinearLayoutManager).let {
                    val itemCount = it.itemCount
                    val lastItem = it.findLastVisibleItemPosition()
                    if (itemCount - lastItem <= PREFETCH_THRESHOLD) {
                        viewModel.loadRepositories()
                    }
                }
            }
        })
        viewModel.repositories.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun itemClicked(repo: Repository) {
        Timber.w(repo.name)
    }
}
