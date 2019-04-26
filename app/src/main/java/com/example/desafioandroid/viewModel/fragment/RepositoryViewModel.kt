package com.example.desafioandroid.viewModel.fragment


import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.desafioandroid.api.RepositoryPagination
import com.example.desafioandroid.schema.RepositoryItem


class RepositoryViewModel: ViewModel(){

    var repositoryProgress: ObservableInt = ObservableInt(View.GONE)
    var repositoryRecycler: ObservableInt = ObservableInt(View.GONE)

    var listRepository : PagedList<RepositoryItem>? = null
    var postsLiveData  : LiveData<PagedList<RepositoryItem>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun initializeViews() {
        repositoryRecycler.set(View.GONE)
        repositoryProgress.set(View.VISIBLE)
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, RepositoryItem> {

        val dataSourceFactory = object : DataSource.Factory<Int, RepositoryItem>() {
            override fun create(): DataSource<Int, RepositoryItem> {
                return RepositoryPagination()
            }
        }
        return LivePagedListBuilder<Int, RepositoryItem>(dataSourceFactory, config)
    }

    fun getRepositoryList(): LiveData<PagedList<RepositoryItem>> = postsLiveData
}