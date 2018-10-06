package com.concrete.andresdavid.desafioandroid

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.concrete.andresdavid.desafioandroid.adapters.RepositoryAdapter
import com.concrete.andresdavid.desafioandroid.viewmodel.RepositoryListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: RepositoryListViewModel by lazy {
        ViewModelProviders.of(this).get(RepositoryListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //se inicializa layout manager
        rv_list_repository.layoutManager = LinearLayoutManager(this)

        viewModel.getJavaRepositories().observe(this, Observer {repositoryList ->
            rv_list_repository.adapter = RepositoryAdapter(repositoryList!!, this)
        })
    }
}
