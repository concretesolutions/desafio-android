package com.example.desafioandroidapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.desafioandroidapp.R
import com.example.desafioandroidapp.data.dto.Item

class RepositoryDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle = intent.extras
        val item = bundle?.getParcelable<Item>(R.string.ITEM.toString())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = item?.name
    }
}