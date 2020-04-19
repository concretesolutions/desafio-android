package com.example.githubtest.data.request

import com.example.githubtest.data.model.RepositoryResponse
import io.reactivex.Observable

interface RepositoryContract {
    fun getRepositories( language: String, sort: String, page: Int): Observable<RepositoryResponse>

}