package com.abs.javarepos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abs.javarepos.R

class ReposActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        supportActionBar?.title = getString(R.string.repos_title)
    }
}
