package br.com.concrete.desafio.feature.repo

import android.arch.lifecycle.LifecycleOwner
import br.com.concrete.desafio.feature.BaseViewModel
import br.com.concrete.sdk.RepoRepository.list
import br.com.concrete.sdk.RepoRepository.requestPage
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.Repo

class RepoListViewModel : BaseViewModel() {

    val repoList = list()

    init {
        handleErrorFor(repoList)
    }

    fun search(owner: LifecycleOwner, page: Int, success: (Page<Repo>) -> Unit, error: (Throwable) -> Unit) = with(requestPage(page)) {
        observeSingleData(owner) {
            it?.let {
                repoList.getData()?.let { data ->
                    data.items.addAll(it.items)
                    data.nextPage = it.nextPage
                }
                success(it)
            }
        }
        observeSingleError(owner, error)
    }

}