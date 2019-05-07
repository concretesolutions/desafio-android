package com.gdavidpb.github.domain.usecase.coroutines

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<Q, W : MutableLiveData<*>>(
    protected open val backgroundContext: CoroutineContext,
    protected open val foregroundContext: CoroutineContext
) {
    private var parentJob = Job()

    abstract fun execute(liveData: W, params: Q)

    protected fun newJob(): Job {
        parentJob = parentJob.run {
            cancelChildren()
            cancel()

            Job()
        }

        return parentJob
    }
}