package com.abs.javarepos.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.abs.javarepos.R
import com.abs.javarepos.model.Repo
import com.abs.javarepos.view.adapter.RepoAdapter
import com.abs.javarepos.viewmodel.ReposViewModel
import kotlinx.android.synthetic.main.activity_repos.*

class ReposActivity : AppCompatActivity() {

    private lateinit var repoAdapter: RepoAdapter
    private lateinit var viewModel: ReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        supportActionBar?.title = getString(R.string.repos_title)

        repoAdapter = RepoAdapter(object :
            RepoAdapter.OnItemClickListener {
            override fun onItemClick(repo: Repo) {
                val intent = Intent(this@ReposActivity, PullRequestsActivity::class.java)
                intent.putExtra(PullRequestsActivity.REPO_KEY, repo)
                startActivity(intent)
            }
        })

        rvRepos.layoutManager = LinearLayoutManager(this)
        rvRepos.adapter = repoAdapter

        viewModel = ViewModelProviders.of(this).get(ReposViewModel::class.java)
        viewModel.repoPagedList.observe(this, Observer { pagedList ->
            pagedList?.let {
                repoAdapter.submitList(it)
            }
        })
    }
}
