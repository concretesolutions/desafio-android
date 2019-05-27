package com.losingtimeapps.domain.boundary

import android.database.Observable
import com.losingtimeapps.domain.Error
import com.losingtimeapps.domain.entity.PullRequest
import com.losingtimeapps.domain.entity.Repository
import io.reactivex.Single

interface ResponseBoundary {

    fun onGetRepository(repoListData: List<Repository>)

    fun onGetPullRequest(pullRequestListData: List<PullRequest>)

    fun onError(error: Error)
}