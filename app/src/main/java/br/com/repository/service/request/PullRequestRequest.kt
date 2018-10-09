package br.com.repository.service.request

import br.com.repository.interfaces.endpoint.PullRequestEndPoint
import br.com.repository.model.Owner
import br.com.repository.model.PullRequest
import br.com.repository.model.Repository
import br.com.repository.service.Api
import io.reactivex.Observable

class PullRequestRequest {

    private val requestPullRequest: PullRequestEndPoint.Pull = Api.retrofit!!.create(
            PullRequestEndPoint.Pull::class.java
    )

    fun getPullRequest(repository: Repository) =
            Api.retrofit!!.create(
                    PullRequestEndPoint.Pull::class.java
            ).callPullRequest(repository.owner.name, repository.nameRepository)
                    .flatMap {
                        lPullRequest-> Observable.fromIterable(lPullRequest)
                            .flatMap {
                                pullRequest-> Observable.just(pullRequest)
                            }
                    }

}
