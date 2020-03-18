package com.example.gitrepositories.modules.repositories

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gitrepositories.R
import com.example.gitrepositories.model.data_sources.RepositoriesDataSourceFactory
import com.example.gitrepositories.model.dto.Repository
import com.example.gitrepositories.modules.ModuleConstants
import com.example.gitrepositories.modules.pull_requests.PullRequestsActivity
import com.example.gitrepositories.model.services.ConnectivityService
import com.example.gitrepositories.model.services.GitHubService
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

class RepositoriesViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private val connectivityService: ConnectivityService by inject()
    private val gitHubService: GitHubService by inject()

    private val context = application.applicationContext
    private val compositeDisposable = CompositeDisposable()

    lateinit var list: LiveData<PagedList<Repository>>
    var intent = MutableLiveData<Intent>()
    var displayEmptyMessage = MutableLiveData<Boolean>()
    var displayConnectivityMessage = MutableLiveData<String>()

    init {
        loadRepositories()
        if (!connectivityService.isNetworkAvailable(context)) {
            displayConnectivityMessage.postValue(context.getString(R.string.no_network_error))
        }
    }

    private fun loadRepositories() {
        val dataSourceFactory = RepositoriesDataSourceFactory(compositeDisposable, gitHubService, ::onInitialFetchCompleted)
        val config = PagedList.Config.Builder().setPageSize(10).setInitialLoadSizeHint(20).setEnablePlaceholders(false).build()
        list = LivePagedListBuilder<Int, Repository>(dataSourceFactory, config).build()
    }

    private fun onInitialFetchCompleted(isEmpty: Boolean) {
        displayEmptyMessage.postValue(isEmpty)
    }

    fun onRepositoryClick(repository: Repository) {
        intent.postValue(Intent(context, PullRequestsActivity::class.java).apply {
            putExtra(ModuleConstants.REPOSITORY_CREATOR.name, repository.username)
            putExtra(ModuleConstants.REPOSITORY_NAME.name, repository.title)
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}