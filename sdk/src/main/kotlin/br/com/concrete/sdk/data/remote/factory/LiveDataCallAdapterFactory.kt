package br.com.concrete.sdk.data.remote.factory

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import br.com.concrete.sdk.extension.toDataResponse
import br.com.concrete.sdk.extension.toErrorResponse
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.type.SUCCESS
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

internal class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != ResponseLiveData::class.java) return null

        val type = getParameterUpperBound(0, returnType as ParameterizedType) as? ParameterizedType
                ?: throw IllegalArgumentException("Resource must be Parameterized")
        return LiveDataCallAdapter<Any>(type)
    }
}

internal class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, ResponseLiveData<R>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): ResponseLiveData<R> {
        return object : ResponseLiveData<R>() {
            private val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) RequestMaker(this::postValue).execute(call)
            }
        }
    }
}

private class RequestMaker<T>(val onRequestFinish: (DataResult<Response<T>>) -> Unit) : AsyncTask<Call<T>, Unit, DataResult<Response<T>>>() {

    override fun doInBackground(vararg calls: Call<T>): DataResult<Response<T>> {
        try {
            return calls[0].execute().toDataResponse(SUCCESS)
        } catch (error: Exception) {
            return error.toErrorResponse()
        }
    }

    override fun onPostExecute(result: DataResult<Response<T>>) = onRequestFinish.invoke(result)

}

internal abstract class ResponseLiveData<T> : LiveData<DataResult<Response<T>>>()