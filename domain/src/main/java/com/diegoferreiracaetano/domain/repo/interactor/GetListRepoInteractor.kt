package com.diegoferreiracaetano.domain.repo.interactor

import androidx.paging.DataSource
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.domain.repo.RepoRepository

class GetListRepoInteractor(private val repository: RepoRepository){

    fun execute(): DataSource.Factory<Int, Repo> {
        return repository.getList()
    }

}