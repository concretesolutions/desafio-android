package com.hako.githubapi.features.pullRequest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hako.githubapi.R

class PullListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pull_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PullListFragment.newInstance(
                    intent.extras?.getString(ARG_PULL_REQUEST_AUTHOR),
                    intent.extras?.getString(ARG_PULL_REQUEST_REPO)))
                .commitNow()
        }
    }

    companion object {
        const val ARG_PULL_REQUEST_AUTHOR = "arg-pull-request-author"
        const val ARG_PULL_REQUEST_REPO = "arg-pull-request-repo"

        @JvmStatic
        fun newIntent(context: Context, author: String, repoName: String): Intent {
            return Intent(context, PullListActivity::class.java).apply {
                putExtra(ARG_PULL_REQUEST_AUTHOR, author)
                putExtra(ARG_PULL_REQUEST_REPO, repoName)
            }
        }
    }
}
