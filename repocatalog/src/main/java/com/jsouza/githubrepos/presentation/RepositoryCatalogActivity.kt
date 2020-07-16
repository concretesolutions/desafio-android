package com.jsouza.githubrepos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jsouza.githubrepos.databinding.ActivityCatalogRepositoryBinding

class RepositoryCatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogRepositoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogRepositoryBinding.inflate(layoutInflater)

        binding.mainToolbarCatalogActivity.mainToolbar.title = " GitHub Repos"
        binding.repositoryRecyclerViewCatalogActivity.adapter = RepositoryCatalogAdapter()
        setContentView(binding.root)
    }
}
