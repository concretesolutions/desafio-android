@file:JvmName("ResponseUtils")

package br.com.concrete.sdk.handler

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import br.com.concrete.sdk.extension.toDataResponse
import br.com.concrete.sdk.extension.toDataResponseWithError
import br.com.concrete.sdk.model.type.LOADING
import br.com.concrete.sdk.model.type.ResponseStatus
import br.com.concrete.sdk.model.type.SUCCESS
import retrofit2.Call
import retrofit2.Callback

data class Response<out T>(
        val data: T?,
        var error: Throwable?,
        @get:ResponseStatus
        @ResponseStatus val status: Long
)

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: ((T) -> Unit)) {
    observe(owner, Observer { it?.let(observer) })
}

internal abstract class ResponseHandler<T>(val cacheMap: HashMap<String, Cache<T>>? = null) : LiveData<Response<T>>() {

    init {
        if (cacheMap != null) {
            val key = this.buildCacheKey()
            val cache = cacheMap[key]
            val cachedData = cache?.data
            val cacheValid = cache?.isValid() ?: false

            if (cacheValid) this.postValue(cachedData.toDataResponse(SUCCESS))
            else if (this.shouldFetch()) {
                this.postValue(cachedData.toDataResponse(LOADING))
                this.requestFromServer().enqueue(object : Callback<T> {
                    override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                        val remoteData = extractDataFromRemoteResponse(response)
                        if (response.isSuccessful && remoteData != null) {
                            saveRemoteData(key, remoteData, cachedData)
                            postValue(remoteData.toDataResponse(SUCCESS))
                        }
                    }

                    override fun onFailure(call: Call<T>, throwable: Throwable) {
                        postValue(cachedData.toDataResponseWithError(throwable))
                    }
                })
            }
        } else {
            this.postValue(null.toDataResponse(LOADING))
            this.requestFromServer().enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                    val remoteData = extractDataFromRemoteResponse(response)
                    if (response.isSuccessful && remoteData != null) postValue(remoteData.toDataResponse(SUCCESS))
                }

                override fun onFailure(call: Call<T>, throwable: Throwable) {
                    postValue(null.toDataResponseWithError(throwable))
                }
            })
        }
    }

    abstract fun requestFromServer(): Call<T>

    abstract fun buildCacheKey(): String

    open fun extractDataFromRemoteResponse(response: retrofit2.Response<T>): T? = response.body()

    open fun saveRemoteData(key: String, remoteData: T, cachedData: T?) {
        cacheMap?.put(key, Cache(data = remoteData))
    }

    open fun shouldFetch() = true

}