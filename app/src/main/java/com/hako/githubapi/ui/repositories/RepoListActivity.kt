package com.hako.githubapi.ui.repositories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hako.githubapi.R

class RepoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repo_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RepoListFragment.newInstance())
                .commitNow()
        }
    }

}
