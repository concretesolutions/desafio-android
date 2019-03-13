package cl.getapps.githubjavarepos.core.interactor

import cl.getapps.githubjavarepos.core.interactor.executor.PostExecutionThread
import cl.getapps.githubjavarepos.core.interactor.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers


/**
 * Abstract class for a UseCase that returns an instance of a [Single].
 */
abstract class FlowableUseCase<T, in Params> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    /**
     * Builds a [Single] which will be used when the current [FlowableUseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: Params): Flowable<T>

    /**
     * Executes the current use case.
     */
    open fun execute(params: Params): Flowable<T> {
        return this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }
}