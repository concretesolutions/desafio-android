package com.jsouza.repocatalog.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.jsouza.repocatalog.databinding.ActivityCatalogRepositoryBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class RepoCatalogActivity : AppCompatActivity() {

    private val viewModel by viewModel<RepoCatalogViewModel>()
    private lateinit var binding: ActivityCatalogRepositoryBinding
    private val repositoriesAdapter = RepoCatalogAdapter()
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
