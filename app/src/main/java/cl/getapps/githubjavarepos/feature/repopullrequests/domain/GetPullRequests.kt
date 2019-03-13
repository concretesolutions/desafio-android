package cl.getapps.githubjavarepos.feature.repopullrequests.domain

import cl.getapps.githubjavarepos.core.exception.Failure
import cl.getapps.githubjavarepos.core.functional.Either
import cl.getapps.githubjavarepos.core.interactor.UseCase
import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequests
import cl.getapps.githubjavarepos.feature.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.feature.repopullrequests.domain.repository.PullRequestsRepository
import io.reactivex.Flowable


class GetPullRequests(private val pullRequestsRepository: PullRequestsRepository) :
    UseCase<Flowable<PullRequests>, PullRequestParams>() {

    override suspend fun run(params: PullRequestParams): Either<Failure, Flowable<PullRequests>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}