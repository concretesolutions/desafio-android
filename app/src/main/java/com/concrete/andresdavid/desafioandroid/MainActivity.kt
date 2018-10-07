package com.concrete.andresdavid.desafioandroid

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.concrete.andresdavid.desafioandroid.adapters.RepositoryAdapter
import com.concrete.andresdavid.desafioandroid.model.Repository
import com.concrete.andresdavid.desafioandroid.model.Resource
import com.concrete.andresdavid.desafioandroid.util.Constants
import com.concrete.andresdavid.desafioandroid.viewmodel.RepositoryListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: RepositoryListViewModel by lazy {
        ViewModelProviders.of(this).get(RepositoryListViewModel::class.java)
    }

    companion object {
        const val REPOSITORY_NAME_KEY = "REPOSITORY_NAME"
        const val REPOSITORY_FULL_NAME_KEY = "REPOSITORY_FULL_NAME"
    }

    var isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init layout manager
        val repositoryListLayoutManager = LinearLayoutManager(this)
        rv_list_repository.layoutManager = repositoryListLayoutManager

        // set adapter
        val repositoryAdapter = RepositoryAdapter(mutableListOf(), this)
        rv_list_repository.adapter = repositoryAdapter

        // add scroll listener
        rv_list_repository.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                try {
                    val visibleItemCount = repositoryListLayoutManager.childCount
                    val totalItemCount = repositoryListLayoutManager.itemCount
                    val pastVisibleItems = repositoryListLayoutManager.findFirstCompletelyVisibleItemPosition()

                    if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                        recyclerView.post {
                            repositoryAdapter.pushItem(Repository("LOADING"))
                            viewModel.load()
                        }
                        isLoading = true
                    }
                } catch (e: Exception) {
                    Log.d("PageException", "Pagination error")
                }
            }
        })

        repositoryAdapter.pushItem(
                Repository("LOADING")
        )
        // add subscription to view model
        viewModel.getJavaRepositories().observe(this, Observer { result ->
            if (repositoryAdapter.itemCount > 0) {
                repositoryAdapter.popItem()
            }

            if (result?.status == Resource.SUCCESS) {
                if (result.data?.isNotEmpty()!!) {
                    if (repositoryAdapter.itemCount > 0) {
                        repositoryAdapter.pushItems(result.data.takeLast(Constants.PAGE_SIZE))
                    } else {
                        repositoryAdapter.pushItems(result.data)
                    }
                }
            }else{
                val toast = Toast.makeText(this, result?.message, Toast.LENGTH_SHORT)
                toast.show()
            }
            isLoading = false
        })
    }
}
