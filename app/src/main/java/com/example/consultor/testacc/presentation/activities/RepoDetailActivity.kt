package com.example.consultor.testacc.presentation.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.consultor.testacc.R
import com.example.consultor.testacc.data.pojos.Repository
import com.example.consultor.testacc.mvvm.view.RepoDetailFragment

class RepoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repo_detail_activity)
        val repo = intent?.getBundleExtra("mybun")
        val myrep = repo?.getSerializable("repopull") as Repository
        loadFragmentRecyler(savedInstanceState, myrep)
    }

    fun loadFragmentRecyler(savedInstance: Bundle?, repository: Repository) {
        if (savedInstance == null) {

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RepoDetailFragment.newInstance(repository))
                .commitNow()
        }

    }


}
