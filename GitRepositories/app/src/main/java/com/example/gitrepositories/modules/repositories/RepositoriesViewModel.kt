package com.example.gitrepositories.modules.repositories

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gitrepositories.R
import com.example.gitrepositories.model.dto.Repository
import com.example.gitrepositories.modules.ModuleConstants
import com.example.gitrepositories.modules.pull_requests.PullRequestsActivity
import com.example.gitrepositories.services.ConnectivityService
import com.example.gitrepositories.services.GitHubService
import org.koin.core.KoinComponent
import org.koin.core.inject

class RepositoriesViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private val connectivityService: ConnectivityService by inject()
    private val gitHubService: GitHubService by inject()

    private val context = application.applicationContext

    lateinit var list: LiveData<PagedList<Repository>>
    var intent = MutableLiveData<Intent>()
    var displayEmptyMessage = MutableLiveData<Boolean>()
    var displayConnectivityMessage = MutableLiveData<String>()

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        if (connectivityService.isNetworkAvailable(context)) {
            gitHubService
            //newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, networkService)
            val config = PagedList.Config.Builder().setPageSize(10).setInitialLoadSizeHint(20)
                .setEnablePlaceholders(false).build()
           // list = LivePagedListBuilder<Int, Repository>(newsDataSourceFactory, config).build()
            displayEmptyMessage.postValue(list.value!!.isEmpty())
        } else {
            displayConnectivityMessage.postValue(context.getString(R.string.no_network_error))
        }
    }

    fun onRepositoryClick(repository: Repository) {
        intent.postValue(Intent(context, PullRequestsActivity::class.java).apply {
            putExtra(ModuleConstants.REPOSITORY_CREATOR.name, repository.username)
            putExtra(ModuleConstants.REPOSITORY_NAME.name, repository.title)
        })
    }
}