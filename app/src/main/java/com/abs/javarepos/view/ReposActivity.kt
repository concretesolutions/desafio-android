package com.abs.javarepos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.abs.javarepos.R
import com.abs.javarepos.viewmodel.ReposViewModel
import kotlinx.android.synthetic.main.activity_repos.*

class ReposActivity : AppCompatActivity() {

    val repoAdapter = RepoAdapter()
    private lateinit var viewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        supportActionBar?.title = getString(R.string.repos_title)

        rvRepos.layoutManager = LinearLayoutManager(this)
        rvRepos.adapter = repoAdapter

        viewModel = ViewModelProviders.of(this).get(ReposViewModel::class.java)
        viewModel.repos.observe(this, Observer {
            repoAdapter.addItems(it)
        })
    }
}
