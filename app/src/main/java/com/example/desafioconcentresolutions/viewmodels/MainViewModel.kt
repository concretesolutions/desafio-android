package com.example.desafioconcentresolutions.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.desafioconcentresolutions.datasource.GitHubrepoDataSourceFactory
import com.example.desafioconcentresolutions.models.GitHubRepo
import com.example.desafioconcentresolutions.models.Resource
import com.example.desafioconcentresolutions.service.GitHubService
import com.example.desafioconcentresolutions.service.IGitHubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val gitHubService: IGitHubService) :
    AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private var mCurrentPageNumber = 1

    private lateinit var pagedList: LiveData<PagedList<GitHubRepo>>

    private val mGithubRepoList = MutableLiveData<Resource<List<GitHubRepo>>>()

    private var gitHubrepoDataSourceFactory: GitHubrepoDataSourceFactory = GitHubrepoDataSourceFactory()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        pagedList = LivePagedListBuilder<Int,GitHubRepo>(gitHubrepoDataSourceFactory, config).build()
    }
    fun getGitHubRepoList() = pagedList

    fun loadFirstPage() {
        if (mCurrentPageNumber == 1) {
            mGithubRepoList.value = Resource.loading()
            ioScope.launch {
                val list = gitHubService.listAllRepoByPage(1)
                mGithubRepoList.postValue(Resource.success(list))
            }
        }
    }
//
//    fun loadNextPage(){
//
//    }


}