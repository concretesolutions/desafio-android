package com.desafioandroid.getirepos.view.mainrepolist

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafioandroid.getirepos.databinding.ActivityMainBinding

class RepoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pageCount = 1

    private val repoListAdapter = RepoListAdapter(object: RepoListAdapter.RepoListActivityListener {
        override fun repoSelected(repoLink: String) {
            onRepoClickCallPull(repoLink)
        }
    })

    private val viewModel: RepoViewModel by viewModels(
        factoryProducer = { RepoViewModelFactory() }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.repoListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.repoListRecyclerView.adapter = repoListAdapter
        fillReposOnCreate()
    }

    fun fillReposOnCreate() {
        viewModel.getRepos(pageCount)
        viewModel.repos.observe(this) {
            value ->
            if(null != value) {
                repoListAdapter.setReposItems(value)
            }
        }
    }

    fun onRepoClickCallPull(pullsLink: String) {

    }
}