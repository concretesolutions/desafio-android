package com.jsouza.repocatalog.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.jsouza.repocatalog.databinding.ActivityCatalogRepositoryBinding
import com.jsouza.repocatalog.domain.`typealias`.StartRepoDetail
import com.jsouza.repocatalog.presentation.adapter.RepoCatalogAdapter
import com.jsouza.repocatalog.presentation.adapter.ReposLoadStateAdapter
import com.jsouza.repodetail.presentation.RepoDetailActivity
import com.jsouza.repodetail.presentation.RepoDetailActivity.Companion.REPO_DETAIL_NAME
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RepoCatalogActivity : AppCompatActivity() {

    private val viewModel by viewModel<RepoCatalogViewModel>()
    private lateinit var binding: ActivityCatalogRepositoryBinding
    private val loadRepoDetail: StartRepoDetail = { String -> startDetailActivity(name = String) }
    private val repositoriesAdapter by inject<RepoCatalogAdapter> { parametersOf(loadRepoDetail) }
    private var showDataJob: Job? = null

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogRepositoryBinding.inflate(layoutInflater)

        initAdapter()
        setupRecyclerView()
        initObserver()

        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.repositoryRecyclerViewCatalogActivity.addItemDecoration(decoration)
        binding.repositoryRecyclerViewCatalogActivity.setHasFixedSize(true)
    }

    private fun startDetailActivity(
        name: String?
    ) {
        val intent = Intent(this, RepoDetailActivity::class.java)
            .apply {
                putExtra(REPO_DETAIL_NAME, name)
            }
        startActivity(intent)
    }

    @ExperimentalCoroutinesApi
    private fun initObserver() {
        showDataJob?.cancel()
        showDataJob = lifecycleScope.launch {
            viewModel.searchRepo().collectLatest {
                repositoriesAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        binding.repositoryRecyclerViewCatalogActivity
            .adapter = repositoriesAdapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { repositoriesAdapter.retry() },
            footer = ReposLoadStateAdapter { repositoriesAdapter.retry() }
        )
    }
}
