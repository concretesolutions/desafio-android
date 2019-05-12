package com.gdavidpb.github.domain.usecase.coroutines

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<Q, W : MutableLiveData<*>>(
    protected open val backgroundContext: CoroutineContext,
    protected open val foregroundContext: CoroutineContext
) {
    private val queue = ConcurrentLinkedQueue<suspend () -> Unit>()

    abstract fun execute(liveData: W, params: Q)

    fun enqueue(liveData: W, params: Q) {
        val newTask = suspend { execute(liveData, params) }

        val canProcess = queue.isEmpty()

        queue.add(newTask)

        if (canProcess) processQueue()
    }

    private fun processQueue() {
        with(queue) {
            if (isNotEmpty())
                CoroutineScope(Dispatchers.Default).launch {
                    do {
                        val executeTask = queue.peek()

                        executeTask()

                        remove()
                    } while (isNotEmpty())
                }
        }
    }
}