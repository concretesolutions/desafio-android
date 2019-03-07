package com.example.lucasdias.mvvmpoc.domain.useCase

import com.example.lucasdias.mvvmpoc.domain.repository.RepositoryRepository

class RepositoryUseCase(private val repositoryRepository: RepositoryRepository) {

    fun getRepositoryList() = repositoryRepository.getRepositoryList()


    fun loadRepositoryPageFromApi(page: Int) {
        repositoryRepository.loadRepositoryPageFromApi(page)
    }

    fun getRequestPermissionStatus() = repositoryRepository.getRequestStatus()
}