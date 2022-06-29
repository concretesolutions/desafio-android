package com.desafioandroid.getirepos.view.mainrepolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.desafioandroid.getirepos.databinding.ActivityMainBinding
import com.desafioandroid.getirepos.view.pullslist.PullsListActivity

class RepoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pageCount = 1

    private val repoListAdapter = RepoListAdapter(object: RepoListAdapter.RepoListActivityListener {
        override fun repoSelected(owner: String, repository: String) {
            onRepoClickCallPull(owner, repository)
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
        fillReposInList()

        binding.repoListRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) {
                    if(!binding.repoListRecyclerView.canScrollVertically(1)) {
                        pageCount++
                        fillReposInList()
                    }
                }
            }
        })
    }

    fun fillReposInList() {
        binding.repoProgressBar.isVisible = true
        viewModel.getRepos(pageCount)
        viewModel.repos.observe(this) {
            value ->
            if(null != value) {
                repoListAdapter.setReposItems(value)
                binding.repoProgressBar.isVisible = false
            }
        }
    }

    fun onRepoClickCallPull(owner: String, repository: String) {
        Toast.makeText(this, "List item clicked!!!", Toast.LENGTH_LONG).show()
        val intent = Intent(this, PullsListActivity::class.java).apply {
            putExtra("owner", owner)
            putExtra("repository", repository)
        }
        startActivity(intent)
    }
}