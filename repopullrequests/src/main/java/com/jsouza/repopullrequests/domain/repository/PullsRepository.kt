package com.jsouza.repopullrequests.domain.repository

import androidx.lifecycle.LiveData
import com.jsouza.repopullrequests.domain.model.PullRequests

interface PullsRepository {

    fun getPullRequests(
        repositoryId: Long
    ): LiveData<List<PullRequests>?>

    suspend fun refreshPullRequests(
        userName: String,
        repoName: String,
        repositoryId: Long
    )
}
