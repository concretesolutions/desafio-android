package com.jsouza.repocatalog.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jsouza.repocatalog.R

class DetailActivity : AppCompatActivity() {
    companion object {
        const val REPO_DETAIL_NAME = "REPO_DETAIL_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val repoName = intent.getStringExtra(REPO_DETAIL_NAME)
        Log.i("api", "$repoName")
    }
}
