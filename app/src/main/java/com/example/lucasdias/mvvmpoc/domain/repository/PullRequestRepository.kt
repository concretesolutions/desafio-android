package com.example.lucasdias.mvvmpoc.domain.repository

import android.arch.lifecycle.LiveData
import com.example.lucasdias.mvvmpoc.domain.entity.PullRequest

interface PullRequestRepository {

    fun getPullRequestList(repositoryId: String): LiveData<List<PullRequest>>?

    fun loadPullRequestsFromApi(fullName: String, repositoryId: String)
}