package com.abs.javarepos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.abs.javarepos.R
import kotlinx.android.synthetic.main.activity_repos.*

class ReposActivity : AppCompatActivity() {

    val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        supportActionBar?.title = getString(R.string.repos_title)

        rvRepos.layoutManager = LinearLayoutManager(this)
        rvRepos.adapter = repoAdapter
    }
}
