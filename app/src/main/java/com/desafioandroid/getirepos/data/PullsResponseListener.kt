package com.desafioandroid.getirepos.data

import com.desafioandroid.getirepos.data.dto.PullsResponse
import com.desafioandroid.getirepos.data.dto.RepositoryError

interface PullsResponseListener {
    fun onPullsResponse(response: PullsResponse)
    fun onError(repositoryError: RepositoryError)
}