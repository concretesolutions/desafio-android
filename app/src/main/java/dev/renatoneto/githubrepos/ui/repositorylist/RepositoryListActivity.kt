package dev.renatoneto.githubrepos.ui.repositorylist

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.renatoneto.githubrepos.R
import dev.renatoneto.githubrepos.base.BaseActivity
import dev.renatoneto.githubrepos.databinding.RepositoryListActivityBinding
import dev.renatoneto.githubrepos.model.github.GithubRepository
import dev.renatoneto.githubrepos.ui.Arguments.ARG_GITHUB_REPOSITORY
import dev.renatoneto.githubrepos.ui.repositorydetails.RepositoryDetailsActivity
import dev.renatoneto.githubrepos.ui.repositorylist.adapter.RepositoryListAdapter
import dev.renatoneto.githubrepos.view.EndlessRecyclerOnScrollListener
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListActivity : BaseActivity(), LifecycleOwner {

    private val listViewModel: RepositoryListViewModel by viewModel()

    private val endlessRecyclerOnScrollListener = object : EndlessRecyclerOnScrollListener() {
        override fun onLoadMore() {
            listViewModel.goToNextPage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_list_activity)

        val binding: RepositoryListActivityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.repository_list_activity
        )

        binding.viewModel = listViewModel

        val adapter = RepositoryListAdapter {
            startActivity<RepositoryDetailsActivity>(ARG_GITHUB_REPOSITORY to it)
        }

        binding.recyclerRepositories.adapter = adapter
        binding.recyclerRepositories.layoutManager = LinearLayoutManager(this)
        binding.recyclerRepositories.addOnScrollListener(endlessRecyclerOnScrollListener)

        listViewModel.repositories.observe(this, Observer<ArrayList<GithubRepository>> {
            adapter.setItems(it!!)
        })

        binding.lifecycleOwner = this

        listViewModel.loadRepositories()
    }

}
