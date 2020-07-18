package com.jsouza.repodetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class RepoDetailActivity : AppCompatActivity() {

    companion object {
        const val REPO_DETAIL_NAME = "REPO_DETAIL_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)

        val repoName = intent.getStringExtra(REPO_DETAIL_NAME)
        Log.i("api", "$repoName")
    }
}
