package br.com.concrete.desafio.feature.pullrequest

import br.com.concrete.desafio.base.BaseViewModel
import br.com.concrete.desafio.data.RepositoryProvider
import br.com.concrete.desafio.data.model.dto.RepoDTO

class PullRequestListViewModel : BaseViewModel() {

    var repo: RepoDTO? = null

    val listPullRequest by lazy { RepositoryProvider.pullRequestRepository.list(repo!!) }

}