package cl.getapps.githubjavarepos.core.interactor

import cl.getapps.githubjavarepos.core.executor.PostExecutionThread
import cl.getapps.githubjavarepos.core.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


abstract class SingleUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    protected abstract fun buildUseCaseObservable(params: Params): Single<T>

    open fun execute(params: Params): Single<T> {
        return this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }
}
