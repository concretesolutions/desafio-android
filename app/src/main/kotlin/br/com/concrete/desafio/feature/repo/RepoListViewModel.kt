package br.com.concrete.desafio.feature.repo

import br.com.concrete.desafio.feature.BaseViewModel
import br.com.concrete.sdk.RepoRepository

class RepoListViewModel : BaseViewModel() {

    val repoList = RepoRepository.list()

    init {
        handleErrorFor(repoList)
    }

    fun search(page: Int) = RepoRepository.requestPage(page)

}