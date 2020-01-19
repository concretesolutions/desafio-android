package com.concretesolutions.desafioandroid.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.concretesolutions.desafioandroid.R

class PullRequestViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_view)

        val actionbar = supportActionBar
        actionbar?.let {
            it.title = "vixi"
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
