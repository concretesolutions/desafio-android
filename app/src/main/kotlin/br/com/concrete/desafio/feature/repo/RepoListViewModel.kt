package br.com.concrete.desafio.feature.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import br.com.concrete.sdk.RepoRepository
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.Repo
import br.com.concrete.sdk.model.DataResult

class RepoListViewModel : ViewModel() {

    fun search(page: Int): LiveData<DataResult<Page<Repo>>> = RepoRepository.search(page)

}