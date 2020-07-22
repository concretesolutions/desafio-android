package com.jsouza.repocatalog.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.jsouza.repocatalog.R
import com.jsouza.repocatalog.databinding.ActivityCatalogRepositoryBinding
import com.jsouza.repocatalog.domain.`typealias`.StartRepoDetail
import com.jsouza.repocatalog.presentation.adapter.RepoCatalogAdapter
import com.jsouza.repocatalog.presentation.adapter.ReposLoadStateAdapter
import com.jsouza.repocatalog.utils.Constants.Companion.ABSOLUTE_ZERO
import com.jsouza.repopullrequests.presentation.PullRequestsActivity
import com.jsouza.repopullrequests.presentation.PullRequestsActivity.Companion.REPO_DETAIL_NAME
import com.jsouza.repopullrequests.presentation.PullRequestsActivity.Companion.REPO_ID
import com.jsouza.repopullrequests.presentation.PullRequestsActivity.Companion.REPO_USER_NAME
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@ExperimentalCoroutinesApi
class RepoCatalogActivity : AppCompatActivity() {

    private val viewModel by viewModel<RepoCatalogViewModel>()
    private val repositoriesAdapter by inject<RepoCatalogAdapter> { parametersOf(loadRepoDetail) }

    private lateinit var binding: ActivityCatalogRepositoryBinding
    private var showDataJob: Job? = null
    private val loadRepoDetail: StartRepoDetail = { repoName, userName, repositoryId ->
        startDetailActivity(
            repoName = repoName,
            userName = userName,
            repositoryId = repositoryId
        )
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogRepositoryBinding.inflate(layoutInflater)

        initAdapter()
        initAdapterLoadListener()
        setupRecyclerView()
        initRepoCollect()
        setupRetryButton()

        setContentView(binding.root)
    }

    private fun initAdapterLoadListener() {
        repositoriesAdapter.addLoadStateListener { loadState ->
            showRecyclerViewOnLoadSuccess(loadState)
            showLoadingSpinnerOnRefreshStatus(loadState)
            showErrorScreen(loadState)
        }
    }

    private fun showRecyclerViewOnLoadSuccess(
        loadState: CombinedLoadStates
    ) {
        when (repositoriesAdapter.itemCount != ABSOLUTE_ZERO) {
            binding.repositoryRecyclerViewCatalogActivity
                .isVisible -> loadState.source.refresh is LoadState.NotLoading
        }
    }

    private fun showLoadingSpinnerOnRefreshStatus(
        loadState: CombinedLoadStates
    ) {
        binding.loadingProgressBarCatalogActivity
            .isVisible = loadState.refresh is LoadState.Loading
    }

    private fun showErrorScreen(
        loadState: CombinedLoadStates
    ) {
        binding.retryButtonCatalogActivity.isVisible =
            repositoriesAdapter.itemCount == ABSOLUTE_ZERO &&
                    loadState.refresh is LoadState.Error

        binding.emptyRepositoriesMessageContainerCatalogActivity.root.isVisible =
            loadState.mediator?.refresh is LoadState.Error &&
                    repositoriesAdapter.itemCount == ABSOLUTE_ZERO
    }

    private fun setupRetryButton() {
        binding.retryButtonCatalogActivity.setOnClickListener {
            repositoriesAdapter.retry()
        }
    }

    private fun setupRecyclerView() {
        val decoration = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL)
        binding.repositoryRecyclerViewCatalogActivity.addItemDecoration(decoration)

        binding.repositoryRecyclerViewCatalogActivity.setHasFixedSize(true)
    }

    private fun startDetailActivity(
        repoName: String?,
        userName: String?,
        repositoryId: Long?
    ) {
        val intent = Intent(this,
            PullRequestsActivity::class.java)
            .apply {
                putExtra(REPO_DETAIL_NAME, repoName)
                putExtra(REPO_USER_NAME, userName)
                putExtra(REPO_ID, repositoryId)
            }

        startActivity(intent)

        overridePendingTransition(R.anim.slide_in_right,
            R.anim.slide_out_left)
    }

    private fun initRepoCollect() {
        showDataJob?.cancel()

        showDataJob = lifecycleScope.launch {
            viewModel.searchRepo().collect {
                repositoriesAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        binding.repositoryRecyclerViewCatalogActivity.adapter =
            repositoriesAdapter.withLoadStateHeaderAndFooter(
                header = ReposLoadStateAdapter { repositoriesAdapter.retry() },
                footer = ReposLoadStateAdapter { repositoriesAdapter.retry() }
            )
    }
}
