package com.concrete.desafio_android.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.concrete.desafio_android.domain.Repository

class PullRequestListActivity: AppCompatActivity() {

    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getParcelableExtra<Repository>("repository")?.let {
            repository = it
        }
    }

}