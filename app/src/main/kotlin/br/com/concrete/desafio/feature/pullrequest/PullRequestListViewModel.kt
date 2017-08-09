package br.com.concrete.desafio.feature.pullrequest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import br.com.concrete.sdk.PullRequestRepository
import br.com.concrete.sdk.handler.Response
import br.com.concrete.sdk.model.PullRequest
import br.com.concrete.sdk.model.Repo

class PullRequestListViewModel : ViewModel() {

    var repo: Repo? = null

    val listPullRequest: LiveData<Response<List<PullRequest>>> by lazy { PullRequestRepository.list(repo!!) }

}