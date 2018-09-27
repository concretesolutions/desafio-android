package br.com.concrete.desafio.feature.repo

import android.arch.lifecycle.LifecycleOwner
import br.com.concrete.desafio.base.BaseViewModel
import br.com.concrete.desafio.data.RepositoryProvider
import br.com.concrete.desafio.data.model.Page
import br.com.concrete.desafio.data.model.dto.RepoDTO

class RepoListViewModel : BaseViewModel() {

    val repoList = RepositoryProvider.repoRepository.list()

    init {
        handleErrorFor(repoList)
    }

    fun search(owner: LifecycleOwner, page: Int, success: (Page<RepoDTO>) -> Unit, error: (Throwable) -> Unit) = with(RepositoryProvider.repoRepository.requestPage(page)) {
        observeSingleData(owner) {
            it?.run {
                repoList.getData()?.let { data ->
                    data.items.addAll(items)
                    data.nextPage = nextPage
                }
                success(this)
            }
        }
        observeSingleError(owner, error)
    }

}