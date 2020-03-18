package com.example.gitrepositories.modules.repositories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepositories.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.repositories.*

class RepositoriesActivity : AppCompatActivity() {

    lateinit var viewModel: RepositoriesViewModel
    lateinit var adapter: RepositoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repositories)

        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)
        setUpAdapter()
        setUpObservers()
    }

    private fun setUpAdapter() {
        adapter = RepositoriesAdapter(this, viewModel::onRepositoryClick)
        repositories_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        repositories_list.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.list.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.intent.observe(this, Observer {
            startActivity(it)
        })

        viewModel.displayEmptyMessage.observe(this, Observer {
            empty_list_text.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.displayConnectivityMessage.observe(this, Observer {
            Snackbar.make(repositories_layout, it, Snackbar.LENGTH_LONG).show()
        })
    }
}