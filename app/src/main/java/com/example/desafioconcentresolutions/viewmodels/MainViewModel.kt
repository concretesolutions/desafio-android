package com.example.desafioconcentresolutions.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.desafioconcentresolutions.datasource.Factory.GitHubPullDataSourceFactory
import com.example.desafioconcentresolutions.datasource.Factory.GitHubRepoDataSourceFactory
import com.example.desafioconcentresolutions.models.GitHubPull
import com.example.desafioconcentresolutions.models.GitHubRepo
import com.example.desafioconcentresolutions.models.Operation

class MainViewModel(application: Application) :
    AndroidViewModel(application) {

    private var gitHubRepoList: LiveData<PagedList<GitHubRepo>>

    private var gitRepoLoadingOperation: LiveData<Operation>

    private lateinit var gitPullLoadingOperation: LiveData<Operation>

    private lateinit var gitHubPullList: LiveData<PagedList<GitHubPull>>

    private var gitHubRepoDataSourceFactory: GitHubRepoDataSourceFactory =
        GitHubRepoDataSourceFactory()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(true)
            .build()

        gitHubRepoList = LivePagedListBuilder<Int,GitHubRepo>(gitHubRepoDataSourceFactory, config).build()

        gitRepoLoadingOperation = Transformations.switchMap(gitHubRepoDataSourceFactory.getGitHubRepoDataSourceLive()) { x -> x.getOperationStatus()}
    }
    fun getGitHubRepoList() = gitHubRepoList

    fun getGitRepoLoadingOperation() = gitRepoLoadingOperation

    fun getGitPullLoadingOperation() = gitPullLoadingOperation

    fun getGitHubPullList() = gitHubPullList

    fun loadPullForUser(ownerName:String, login:String){
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        val gitHubPullDataSourceFactory = GitHubPullDataSourceFactory(ownerName, login)
        gitPullLoadingOperation = Transformations.switchMap(gitHubPullDataSourceFactory.getGitHubPullDataSourceLive()) { dataSource -> dataSource.getOperationStatus()}
        gitHubPullList = LivePagedListBuilder<Int,GitHubPull>(gitHubPullDataSourceFactory, config).build()
    }


}