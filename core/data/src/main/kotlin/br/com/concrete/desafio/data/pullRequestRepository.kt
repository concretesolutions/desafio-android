package br.com.concrete.desafio.data

import android.support.annotation.RestrictTo
import br.com.concrete.desafio.data.livedata.ResponseLiveData
import br.com.concrete.desafio.data.model.dto.PullRequestDTO
import br.com.concrete.desafio.data.model.dto.RepoDTO
import br.com.concrete.desafio.data.remote.api

interface PullRequestRepository {

    fun list(repo: RepoDTO): ResponseLiveData<List<PullRequestDTO>>
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
internal class PullRequestRepositoryImpl : PullRequestRepository {

    override fun list(repo: RepoDTO) =
            api.listPullRequest(creator = repo.owner.login, repo = repo.name)
}