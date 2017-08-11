package br.com.concrete.sdk.handler

import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import br.com.concrete.sdk.data.remote.factory.ResponseLiveData
import br.com.concrete.sdk.extension.nextPage
import br.com.concrete.sdk.extension.receiveData
import br.com.concrete.sdk.extension.toDataResponse
import br.com.concrete.sdk.extension.toDataResponseWithError
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.type.LOADING
import br.com.concrete.sdk.model.type.SUCCESS
import retrofit2.Response

internal abstract class ResponseHandler<T> : MediatorLiveData<DataResult<T>>() {

    init {
        value = null.toDataResponse(LOADING) // Deliver Loading

        val localData = this.localData()
        val shouldFetch = this.shouldFetch(localData)

        // Deliver Preload if needed
        localData.takeIf { shouldPreLoad(it) }?.let {
            value = it.data.toDataResponse(if (shouldFetch) LOADING else SUCCESS)
        }

        // Fetch Data
        if (shouldFetch) fetchData()
    }

    private fun fetchData() {
        receiveData(remoteData()) {
            it.data?.let(this::extractDataFromResponse)?.let {
                saveRemoteResult(it)
                postValue(it.toDataResponse(SUCCESS))
            } ?: it.error?.let(this::onFetchFailed)
        }
    }

    @MainThread
    protected open fun onFetchFailed(error: Throwable) = postValue(value?.data.toDataResponseWithError(error))

    @MainThread
    protected open fun saveRemoteResult(remoteData: T) = Unit

    @MainThread
    protected open fun shouldFetch(localData: Cache<T>?) = !(localData?.isValid() ?: false)

    @MainThread
    protected abstract fun remoteData(): ResponseLiveData<T>

    @MainThread
    protected open fun extractDataFromResponse(response: Response<T>): T {
        val body = response.body() ?: throw IllegalStateException("Body is Null!")
        if (body is Page<*>) body.nextPage = response.nextPage()
        return body
    }

    @MainThread
    protected open fun shouldPreLoad(localData: Cache<T>?) = localData?.data != null ?: false

    @MainThread
    protected open fun localData(): Cache<T>? = null

}