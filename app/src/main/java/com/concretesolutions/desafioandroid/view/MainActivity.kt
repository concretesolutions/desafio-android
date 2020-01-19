package com.concretesolutions.desafioandroid.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.adapters.RepositoryAdapter
import com.concretesolutions.desafioandroid.viewmodel.RepositoriesViewModel
import com.concretesolutions.desafioandroid.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var page: Int = 1
    private val sortType: String = "stars"
    private val searchTerm: String = "language:Java"
    private lateinit var repositoryAdapter: RepositoryAdapter
    private val repositoriesViewModel: RepositoriesViewModel = RepositoriesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapters()
        initView()

    }

    private fun initAdapters() {
        repositoryAdapter = RepositoryAdapter( object: RepositoryAdapter.OnItemClickListener{
            override fun onItemClick(repositoryViewModel: RepositoryViewModel) { repoClicked(repositoryViewModel) }
        })
        loadPageRepos()
        repositoriesViewModel.getRepos().observe(this, Observer {
            progressBar.visibility = View.GONE
            repositoryAdapter.updateRepositories(it!!.repositories)
        })

    }

    private fun loadPageRepos() {
        progressBar.visibility = View.VISIBLE
        repositoriesViewModel.loadRepos(searchTerm, sortType, page)
    }

    private fun initView() {

        val linearVertical = LinearLayoutManager(this)
        linearVertical.orientation = LinearLayoutManager.VERTICAL
        rvRepositories.layoutManager = linearVertical
        rvRepositories.adapter = repositoryAdapter

        rvRepositories.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                layoutManager.let {

                    if( it.findLastVisibleItemPosition() + 6 > it.itemCount) {
                        page++
                        loadPageRepos()
                    }
                }



            }

        })
    }

    private fun repoClicked(repositoryViewModel: RepositoryViewModel) {

        val intent = Intent(applicationContext, PullRequestViewActivity::class.java)
        intent.putExtra("repository", repositoryViewModel.repositoryData.name)
        intent.putExtra("owner", repositoryViewModel.repositoryData.owner.login)
        startActivity(intent)

    }
}
