package br.com.concrete.desafio.feature.pullrequest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import br.com.concrete.sdk.PullRequestRepository
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo
import br.com.concrete.sdk.model.DataResult

class PullRequestListViewModel : ViewModel() {

    var repo: Repo? = null

    val listPullRequest: LiveData<DataResult<List<PullRequest>>> by lazy { PullRequestRepository.list(repo!!) }

}