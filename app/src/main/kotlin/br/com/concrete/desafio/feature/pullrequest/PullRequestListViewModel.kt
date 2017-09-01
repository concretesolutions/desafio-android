package br.com.concrete.desafio.feature.pullrequest

import android.arch.lifecycle.ViewModel
import br.com.concrete.sdk.PullRequestRepository
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo

class PullRequestListViewModel : ViewModel() {

    var repo: Repo? = null

    val listPullRequest: ResponseLiveData<List<PullRequest>> by lazy { PullRequestRepository.list(repo!!) }

}