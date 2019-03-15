package cl.getapps.githubjavarepos.features.repopullrequests.domain.usecase

import cl.getapps.githubjavarepos.core.executor.PostExecutionThread
import cl.getapps.githubjavarepos.core.executor.ThreadExecutor
import cl.getapps.githubjavarepos.core.interactor.SingleUseCase
import cl.getapps.githubjavarepos.features.repopullrequests.data.entity.PullRequestsResponse
import cl.getapps.githubjavarepos.features.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.features.repopullrequests.domain.repository.PullRequestsRepository
import io.reactivex.Single


class GetPullRequests(
    private val pullRequestsRepository: PullRequestsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<PullRequestsResponse, PullRequestParams>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: PullRequestParams): Single<PullRequestsResponse> {
        return pullRequestsRepository.fetchPullRequests(params)
    }

}
