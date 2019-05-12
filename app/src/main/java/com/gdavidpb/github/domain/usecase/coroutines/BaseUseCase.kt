package com.gdavidpb.github.domain.usecase.coroutines

import androidx.lifecycle.MutableLiveData
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<Q, W : MutableLiveData<*>>(
    protected open val backgroundContext: CoroutineContext,
    protected open val foregroundContext: CoroutineContext
) {
    abstract fun execute(liveData: W, params: Q)
}