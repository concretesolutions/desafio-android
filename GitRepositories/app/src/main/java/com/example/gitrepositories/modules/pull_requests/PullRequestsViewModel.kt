package com.example.gitrepositories.modules.pull_requests

import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gitrepositories.R
import com.example.gitrepositories.model.data_sources.PullRequestsDataSourceFactory
import com.example.gitrepositories.model.dto.PullRequest
import com.example.gitrepositories.model.services.ConnectivityService
import org.koin.core.KoinComponent
import org.koin.core.inject

class PullRequestsViewModel(application: Application, private val repoName: String, private val repoCreator: String) : AndroidViewModel(application), KoinComponent {

    private val connectivityService: ConnectivityService by inject()

    private val context = application.applicationContext

    lateinit var list: LiveData<PagedList<PullRequest>>
    var intent = MutableLiveData<Intent>()
    var displayEmptyMessage = MutableLiveData<Boolean>()
    var displayNoBrowserMessage = MutableLiveData<String>()
    var displayConnectivityMessage = MutableLiveData<String>()
    var displayLoadPullRequestsError = MutableLiveData<String>()

    init {
        loadPullRequests()
        if (!connectivityService.isNetworkAvailable(context)) {
            displayConnectivityMessage.postValue(context.getString(R.string.no_network_error))
        }
    }

    private fun loadPullRequests() {
        val dataSourceFactory = PullRequestsDataSourceFactory(::onInitialFetchCompleted, ::onLoadPullRequestsError, repoCreator, repoName)
        val config = PagedList.Config.Builder().setPageSize(30).setInitialLoadSizeHint(30).setEnablePlaceholders(false).build()
        list = LivePagedListBuilder<Int, PullRequest>(dataSourceFactory, config).build()
    }

    private fun onInitialFetchCompleted(isEmpty: Boolean) {
        displayEmptyMessage.postValue(isEmpty)
    }

    private fun onLoadPullRequestsError() {
        displayLoadPullRequestsError.postValue(context.getString(R.string.load_pr_error))
    }

    fun onPullRequestClick(pullRequest: PullRequest) {
        try {
            intent.postValue(Intent(Intent.ACTION_VIEW, getValidUri(pullRequest.link)))
        } catch (e: ActivityNotFoundException) {
            displayNoBrowserMessage.postValue(context.getString(R.string.browser_error))
        }
    }

    private fun getValidUri(url: String): Uri? {
        return if (!url.startsWith("http://") && !url.startsWith("https://")) {
            Uri.parse("http://$url")
        } else {
            Uri.parse(url)
        }
    }
}