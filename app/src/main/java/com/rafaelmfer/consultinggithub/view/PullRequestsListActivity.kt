package com.rafaelmfer.consultinggithub.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rafaelmfer.consultinggithub.R
import kotlinx.android.synthetic.main.activity_pull_requests_list.*

class PullRequestsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests_list)
        setSupportActionBar(toolbarPullRequests)


    }

}
