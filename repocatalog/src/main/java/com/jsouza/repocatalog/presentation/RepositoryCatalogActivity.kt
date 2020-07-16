package com.jsouza.repocatalog.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jsouza.githubrepos.databinding.ActivityCatalogRepositoryBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RepositoryCatalogActivity : AppCompatActivity() {

    private val viewModel by viewModel<RepositoryCatalogViewModel>()
    private lateinit var binding: ActivityCatalogRepositoryBinding
    private val adapter = RepositoryCatalogAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogRepositoryBinding.inflate(layoutInflater)

        binding.mainToolbarCatalogActivity.mainToolbar.title = " GitHub Repos"
        binding.repositoryRecyclerViewCatalogActivity.adapter = adapter

        initObserver()

        setContentView(binding.root)
    }

    private fun initObserver() {
        viewModel.apply {
            this.repoList.observe(this@RepositoryCatalogActivity, Observer {
                adapter.submitList(it)
            })
        }
    }
}
