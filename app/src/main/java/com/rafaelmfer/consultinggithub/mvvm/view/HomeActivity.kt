package com.rafaelmfer.consultinggithub.mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import com.rafaelmfer.consultinggithub.R
import com.rafaelmfer.consultinggithub.mvvm.ViewModelFactory
import com.rafaelmfer.consultinggithub.mvvm.viewmodel.GitHubRepositoriesViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), SwipyRefreshLayout.OnRefreshListener {

    private lateinit var gitHubRepositoriesListAdapter: GitHubRepositoriesListAdapter
    private val gitHubRepositoriesViewModel by lazy {
        ViewModelProviders
            .of(this, ViewModelFactory())
            .get(GitHubRepositoriesViewModel::class.java)
    }
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbarHome)
        swipyrefreshlayout.setOnRefreshListener(this@HomeActivity)

        setList()
        setObservers()

        getRepositories()
//        gitHubRepositoriesViewModel.getRepositoriesList(page)
    }

    private val listener = object : OnClickListenerGitHub {
        override fun onClickOpenPullRequestsList(creator: String, repositoryId: String) {
            startActivity(Intent(this@HomeActivity, PullRequestsListActivity::class.java).apply {
                putExtra("repoId", repositoryId)
                putExtra("creator", creator)
            })
        }
    }

    private fun setList() {
        rvRepositoriesList.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            gitHubRepositoriesListAdapter = GitHubRepositoriesListAdapter(listener)
            adapter = gitHubRepositoriesListAdapter
        }
    }

    private fun setObservers() {
        gitHubRepositoriesViewModel.command.observe(this, Observer {
            when (it) {
                is GitHubRepositoriesViewModel.Command.ShowLoading -> showLoading(true)
                is GitHubRepositoriesViewModel.Command.HideLoading -> showLoading(false)
            }
        })

        gitHubRepositoriesViewModel.gitHubRepositories.observe(this, Observer { repositoriesList ->
            if (repositoriesList != null) {
                gitHubRepositoriesListAdapter.run {
                    addPage(repositoriesList.items)
//                    notifyDataSetChanged()
                }
                swipyrefreshlayout.isRefreshing = false
            }
        })

        gitHubRepositoriesViewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
        })
    }

    private fun showLoading(visible: Boolean) {
        rvRepositoriesList.visibility = if (visible) GONE else VISIBLE
    }

    override fun onRefresh(direction: SwipyRefreshLayoutDirection) {
        getRepositories()
        Toast.makeText(this, "carregando sabosta", Toast.LENGTH_LONG).show()
    }

    private fun getRepositories() {
        gitHubRepositoriesViewModel.getRepositoriesList(page)
        page++
    }
}
