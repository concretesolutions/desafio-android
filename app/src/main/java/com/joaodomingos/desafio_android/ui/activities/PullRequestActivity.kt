package com.joaodomingos.desafio_android.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joaodomingos.desafio_android.R

class PullRequestActivity : AppCompatActivity() {

    lateinit var ownerName: String
    lateinit var repositoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)

        ownerName = intent.getStringExtra("ownerName")
        repositoryName = intent.getStringExtra("repositoryName")
    }
}
