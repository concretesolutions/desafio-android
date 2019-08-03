package br.com.concrete.githubconcretechallenge.features.repositories.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.concrete.githubconcretechallenge.R
import br.com.concrete.githubconcretechallenge.features.pullrequests.view.PullRequestsActivity
import br.com.concrete.githubconcretechallenge.features.repositories.viewmodel.RepositoriesListViewModel
import kotlinx.android.synthetic.main.activity_repositories_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class RepositoriesListActivity : AppCompatActivity() {

    private val viewModel: RepositoriesListViewModel by viewModel()

    private val adapter: RepositoriesPagedAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories_list)

        configRecyclerView()

        createEvents()

        addObservers()
    }

    private fun configRecyclerView() {
        recycler_repository_list.adapter = adapter
        recycler_repository_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun createEvents() {
        swipe_refresh_repositories_list.setOnRefreshListener {
            viewModel.refreshData()
            Toast.makeText(this, R.string.invalidating_cache, Toast.LENGTH_LONG).show()
        }

        adapter.onItemClicked = { repositoryModel ->
            PullRequestsActivity.start(this, repositoryModel)
        }
    }

    private fun addObservers() {

        swipe_refresh_repositories_list.isRefreshing = true
        viewModel.getRepositories().observe(this, Observer { pagedList ->
            adapter.submitList(pagedList)
            swipe_refresh_repositories_list.isRefreshing = false
        })

    }

}
