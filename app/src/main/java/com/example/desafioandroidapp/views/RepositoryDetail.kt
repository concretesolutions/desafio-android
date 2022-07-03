package com.example.desafioandroidapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.desafioandroidapp.R

class RepositoryDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_details)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }
}