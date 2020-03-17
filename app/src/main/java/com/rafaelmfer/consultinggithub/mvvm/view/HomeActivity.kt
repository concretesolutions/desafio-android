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
import com.rafaelmfer.consultinggithub.R
import com.rafaelmfer.consultinggithub.mvvm.ViewModelFactory
import com.rafaelmfer.consultinggithub.mvvm.viewmodel.GitHubRepositoriesViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    private val gitHubRepositoriesViewModel by lazy {
        ViewModelProviders
            .of(this, ViewModelFactory())
            .get(GitHubRepositoriesViewModel::class.java)
    }
    private val page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbarHome)

        setList()
        setObservers()

        gitHubRepositoriesViewModel.getRepositoriesList(page)
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
            adapter = GitHubRepositoriesListAdapter(emptyList(), listener)
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
                rvRepositoriesList.adapter = GitHubRepositoriesListAdapter(repositoriesList.items, listener)
            }
        })

        gitHubRepositoriesViewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
        })
    }

    private fun showLoading(visible: Boolean) {
        rvRepositoriesList.visibility = if (visible) GONE else VISIBLE
    }
}
