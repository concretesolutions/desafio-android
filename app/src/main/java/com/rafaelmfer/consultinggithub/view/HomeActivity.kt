package com.rafaelmfer.consultinggithub.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.rafaelmfer.consultinggithub.R
import com.rafaelmfer.consultinggithub.ViewModelFactory
import com.rafaelmfer.consultinggithub.viewmodel.GitHubRepositoriesViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    private val gitHubRepositoriesViewModel by lazy { ViewModelProviders.of(this, ViewModelFactory()).get(GitHubRepositoriesViewModel::class.java) }
    private val page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbarHome)

        setList()
        setObservers()

        gitHubRepositoriesViewModel.getRepositoriesList(page)

    }

    private fun setList() {
        rvRepositoriesList.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = GitHubRepositoriesListAdapter(emptyList())
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
                rvRepositoriesList.adapter = GitHubRepositoriesListAdapter(repositoriesList.items)
            }
        })

        gitHubRepositoriesViewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
        })
    }

    private fun showLoading(visible: Boolean) {
        if (visible) {
            rvRepositoriesList.visibility = View.GONE
//            progressBar.visibility = View.VISIBLE
        } else {
            rvRepositoriesList.visibility = View.VISIBLE
//            progressBar.visibility = View.GONE
        }
    }
}
