package br.com.concrete.desafio.feature.pullrequest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import br.com.concrete.sdk.RepoRepository
import br.com.concrete.sdk.handler.Response
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.Repo

class RepoListViewModel : ViewModel() {

    fun search(page: Int): LiveData<Response<Page<Repo>>> = RepoRepository.search(page)

}