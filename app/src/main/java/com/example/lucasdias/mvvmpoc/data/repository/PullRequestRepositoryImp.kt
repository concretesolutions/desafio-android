package com.example.lucasdias.mvvmpoc.data.repository

import android.arch.lifecycle.LiveData
import com.example.lucasdias.mvvmpoc.data.db.AppDatabase
import com.example.lucasdias.mvvmpoc.domain.entity.PullRequest
import com.example.lucasdias.mvvmpoc.data.service.PullRequestService
import com.example.lucasdias.mvvmpoc.domain.repository.PullRequestRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import timber.log.Timber

class PullRequestRepositoryImp(private val service: PullRequestService, private val database: AppDatabase): PullRequestRepository {

    override fun getPullRequestList(repositoryId: String): LiveData<List<PullRequest>>? {
        return database.pullRequestDao().getPullRequestList(repositoryId)
    }

    override fun loadPullRequestsFromApi(fullName: String, repositoryId: String) {

        GlobalScope.async {
            val pullRequestListResponse = service.loadPullRequestPageFromApi(fullName).await()
            val responseIsEmpty = pullRequestListResponse.body()?.isEmpty() ?: true

        if(responseIsEmpty) {
            val pullRequestList =  database.pullRequestDao().getPullRequestsMutableList(repositoryId)
            val mutableListIsEmpty = pullRequestList.isEmpty()

            if(mutableListIsEmpty) {
                val pullRequest = PullRequest(0, "Não há pullrequests :)",
                        "Neste repositório não há nenhum pullrequest pendente", "", "", repositoryId, null)
                pullRequestList.add(pullRequest)
                database.pullRequestDao().insertPullRequestList(pullRequestList)
            }
        }
        else {
            pullRequestListResponse.body()?.forEach {
                it.repositoryId = repositoryId
            }
            database.pullRequestDao().insertPullRequestList(pullRequestListResponse.body())
        }
            Timber.i("loadPullRequestsFromApi - response body${pullRequestListResponse.body()}")
        }
    }

}