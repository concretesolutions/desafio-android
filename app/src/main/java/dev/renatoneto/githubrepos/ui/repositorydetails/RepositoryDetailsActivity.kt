package dev.renatoneto.githubrepos.ui.repositorydetails

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.renatoneto.githubrepos.R
import dev.renatoneto.githubrepos.base.BaseActivity
import dev.renatoneto.githubrepos.databinding.RepositoryDetailsActivityBinding
import dev.renatoneto.githubrepos.model.github.GithubPullRequest
import dev.renatoneto.githubrepos.ui.Arguments.ARG_GITHUB_REPOSITORY
import dev.renatoneto.githubrepos.ui.repositorydetails.adapter.RepositoryDetailsListAdapter
import org.jetbrains.anko.browse
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RepositoryDetailsActivity : BaseActivity(), LifecycleOwner {

    private val detailsViewModel: RepositoryDetailsViewModel by viewModel {
        parametersOf(intent?.extras?.getParcelable(ARG_GITHUB_REPOSITORY))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_details_activity)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = detailsViewModel.githubRepository.name
        }

        val binding: RepositoryDetailsActivityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.repository_details_activity
        )

        binding.viewModel = detailsViewModel

        val adapter = RepositoryDetailsListAdapter {
            browse(it.htmlUrl)
        }

        binding.recyclerPullRequests.adapter = adapter
        binding.recyclerPullRequests.layoutManager = LinearLayoutManager(this)

        detailsViewModel.pullRequests.observe(this, Observer<ArrayList<GithubPullRequest>> {
            adapter.setItems(it!!)
        })

        binding.lifecycleOwner = this

        detailsViewModel.loadPullRequests()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
