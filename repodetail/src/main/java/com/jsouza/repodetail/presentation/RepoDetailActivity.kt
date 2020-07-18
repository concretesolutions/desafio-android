package com.jsouza.repodetail.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jsouza.repodetail.R
import org.koin.android.viewmodel.ext.android.viewModel

class RepoDetailActivity : AppCompatActivity() {

    companion object {
        const val REPO_DETAIL_NAME = "REPO_DETAIL_NAME"
    }

    private val viewModel by viewModel<RepoDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)

        val repoName = intent.getStringExtra(REPO_DETAIL_NAME)
        Log.i("api", "$repoName")

        initObserver()
    }

    private fun initObserver() {
        viewModel.apply {
            this.returnPulls?.observe(this@RepoDetailActivity, Observer {
                Log.i("api", "$it")
            })
        }
    }
}
