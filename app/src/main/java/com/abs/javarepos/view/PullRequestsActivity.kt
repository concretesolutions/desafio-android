package com.abs.javarepos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abs.javarepos.R
import com.abs.javarepos.model.Repo

class PullRequestsActivity : AppCompatActivity() {

    companion object {
        const val REPO_KEY = "repo_key"
    }

    lateinit var repo: Repo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        repo = intent.getSerializableExtra(REPO_KEY) as Repo
    }
}
