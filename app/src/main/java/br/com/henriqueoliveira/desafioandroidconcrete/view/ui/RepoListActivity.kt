package br.com.henriqueoliveira.desafioandroidconcrete.view.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriqueoliveira.desafioandroidconcrete.R
import br.com.henriqueoliveira.desafioandroidconcrete.helpers.EndlessRecyclerViewScrollListener
import br.com.henriqueoliveira.desafioandroidconcrete.helpers.launchActivity
import br.com.henriqueoliveira.desafioandroidconcrete.helpers.show
import br.com.henriqueoliveira.desafioandroidconcrete.helpers.showSnack
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.Repository
import br.com.henriqueoliveira.desafioandroidconcrete.service.repository.Resource
import br.com.henriqueoliveira.desafioandroidconcrete.view.adapter.RepositoriesAdapter
import br.com.henriqueoliveira.desafioandroidconcrete.viewmodel.RepositoryListViewModel
import kotlinx.android.synthetic.main.activity_repo_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RepoListActivity : BaseActivity() {

    val repoViewModel by viewModel<RepositoryListViewModel>()
    private lateinit var adapter: RepositoriesAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)

        setupToolbar(toolbar, R.string.app_name, -1, false)

        repoViewModel.loadRepositories()
        configRecycler()
        handleViewModel()
        setListeners()

    }

    private fun configRecycler() {
        adapter = RepositoriesAdapter(arrayListOf()) { repository -> onRepositoryItemClick(repository) }
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerRepositories.layoutManager = layoutManager
        recyclerRepositories.adapter = adapter
    }

    private fun setListeners() {

        recyclerRepositories.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(totalItemsCount: Int, view: RecyclerView) {
                repoViewModel.loadNextPage()
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            repoViewModel.refreshList()
        }

    }

    private fun handleViewModel() {

        repoViewModel.isLoading.observe(this, Observer {
            it?.let { swipeRefreshLayout.isRefreshing = it }
        })


        repoViewModel.state.observe(this, Observer {
            when (it.status) {

                Resource.RequestStatus.SUCCESS -> {
                    it.data?.let { adapter.updateList(it) }
                }
                Resource.RequestStatus.ERROR -> {
                    it.data?.let { adapter.updateList(it) }
                    it.message?.let { showSnack(it) }
                }
                Resource.RequestStatus.LOADING ->  swipeRefreshLayout.isRefreshing = true
            }
        })


        recyclerRepositories.addOnScrollListener(object : EndlessRecyclerViewScrollListener(LinearLayoutManager(this)) {
            override fun onLoadMore(totalItemsCount: Int, view: RecyclerView) {
                repoViewModel.loadNextPage()
            }
        })

    }

    private fun onRepositoryItemClick(repository: Repository) {
        val extras = Bundle()
        extras.putString(PullRequestActivity.EXTRA_REPOSITORY_NAME,  repository.repoName)
        extras.putString(PullRequestActivity.EXTRA_REPOSITORY_OWNER, repository.owner.login)
        launchActivity<PullRequestActivity>(extras)
    }


}
