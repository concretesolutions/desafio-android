package com.example.desafioandroid.viewModel.fragment


import android.view.View
import androidx.databinding.ObservableField
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
    var repositoryLabel: ObservableInt = ObservableInt(View.VISIBLE)
    var messageLabel: ObservableField<String> = ObservableField("No se cargo nada")

    var postsLiveData  : LiveData<PagedList<RepositoryItem>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun initializeViews() {
        repositoryLabel.set(View.GONE)
        repositoryRecycler.set(View.GONE)
        repositoryProgress.set(View.VISIBLE)
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, RepositoryItem> {

        val dataSourceFactory = object : DataSource.Factory<String, RepositoryItem>() {
            override fun create(): DataSource<String, RepositoryItem> {
                return RepositoryPagination()
            }
        }
        return LivePagedListBuilder<String, RepositoryItem>(dataSourceFactory, config)
    }

    fun getRepositoryList(): LiveData<PagedList<RepositoryItem>> = postsLiveData

    fun goneView() {
        repositoryLabel.set(View.GONE)
        repositoryRecycler.set(View.VISIBLE)
        repositoryProgress.set(View.GONE)
    }
}