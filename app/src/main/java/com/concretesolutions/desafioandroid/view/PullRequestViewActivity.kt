package com.concretesolutions.desafioandroid.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.databinding.ActivityPullRequestViewBinding

class PullRequestViewActivity : AppCompatActivity() {

    private lateinit var bindig: ActivityPullRequestViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindig = DataBindingUtil.setContentView(this, R.layout.activity_pull_request_view)

        initView(intent.extras)
    }

    private fun initView(bundle: Bundle) {
        val reposFullName = bundle.getString("repositoryName")
        val actionbar = supportActionBar
        actionbar?.let {
            it.title = reposFullName
            it.setDisplayHomeAsUpEnabled(true)
        }
        bindig.opened = "17 opened"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
