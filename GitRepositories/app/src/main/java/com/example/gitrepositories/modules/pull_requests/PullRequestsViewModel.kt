package com.example.gitrepositories.modules.pull_requests

import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.gitrepositories.R
import com.example.gitrepositories.model.dto.PullRequest
import com.example.gitrepositories.services.ConnectivityService
import com.example.gitrepositories.services.GitHubService
import kotlinx.android.synthetic.main.repository_item.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject


class PullRequestsViewModel(application: Application, private val repoName: String, private val repoCreator: String)
    : AndroidViewModel(application), KoinComponent {

    private val connectivityService: ConnectivityService by inject()
    private val gitHubService: GitHubService by inject()

    private val context = application.applicationContext

    lateinit var list: LiveData<PagedList<PullRequest>>
    var intent = MutableLiveData<Intent>()
    var displayEmptyMessage = MutableLiveData<Boolean>()
    var displayNoBrowserMessage = MutableLiveData<String>()
    var displayConnectivityMessage = MutableLiveData<String>()

    init {
        loadPullRequests()
    }

    private fun loadPullRequests() {
        if (connectivityService.isNetworkAvailable(context)) {
            gitHubService
           // newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, networkService)
            val config = PagedList.Config.Builder().setPageSize(10).setInitialLoadSizeHint(20)
                .setEnablePlaceholders(false).build()
          //  list = LivePagedListBuilder<Int, PullRequest>(newsDataSourceFactory, config).build()
            displayEmptyMessage.postValue(list.value!!.isEmpty())
        } else {
            displayConnectivityMessage.postValue(context.getString(R.string.no_network_error))
        }
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