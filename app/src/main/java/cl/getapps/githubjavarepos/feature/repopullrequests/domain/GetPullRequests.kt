package cl.getapps.githubjavarepos.feature.repopullrequests.domain

import cl.getapps.githubjavarepos.core.interactor.FlowableUseCase
import cl.getapps.githubjavarepos.core.interactor.executor.PostExecutionThread
import cl.getapps.githubjavarepos.core.interactor.executor.ThreadExecutor
import cl.getapps.githubjavarepos.feature.repopullrequests.data.PullRequests
import cl.getapps.githubjavarepos.feature.repopullrequests.data.remote.PullRequestParams
import cl.getapps.githubjavarepos.feature.repopullrequests.domain.repository.PullRequestsRepository
import io.reactivex.Flowable


class GetPullRequests(
    private val pullRequestsRepository: PullRequestsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<PullRequests, PullRequestParams>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: PullRequestParams): Flowable<PullRequests> {
        return pullRequestsRepository.fetchPullRequests(params)
    }

}
