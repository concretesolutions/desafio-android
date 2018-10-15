package com.rafaelpereiraramos.desafioAndroid.view.pull

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rafaelpereiraramos.desafioAndroid.App
import com.rafaelpereiraramos.desafioAndroid.database.model.PullTO
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO
import com.rafaelpereiraramos.desafioAndroid.repository.PullRepository
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 14/10/2018.
 */
class PullViewModel @Inject constructor(pullRepository: PullRepository) : ViewModel() {

    private var repo = MutableLiveData<RepoTO>()

    var pulls: LiveData<PagedList<PullTO>> =
            Transformations.switchMap(repo) {
                it -> LivePagedListBuilder(
                        pullRepository.getPulls(it.owner.login, it.name), App.LOCAL_SNAPSHOT_SIZE)
                        .setBoundaryCallback(pullRepository)
                        .build()
            }

    var networkError: LiveData<String> =
            Transformations.map(pullRepository.networkErrors){it -> it}

    fun getPulls(repo: RepoTO) = this.repo.postValue(repo)
}