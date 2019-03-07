package com.example.lucasdias.mvvmpoc.domain.useCase

import com.example.lucasdias.mvvmpoc.domain.repository.PullRequestRepository

class PullRequestUseCase (private val pullRequestRepository: PullRequestRepository){

    fun getPullRequestList(repositoryId: String) = pullRequestRepository.getPullRequestList(repositoryId)


    fun loadPullRequestsFromApi(repositoryFullName: String, repositoryId: String) {
        pullRequestRepository.loadPullRequestsFromApi(repositoryFullName, repositoryId)
    }
}