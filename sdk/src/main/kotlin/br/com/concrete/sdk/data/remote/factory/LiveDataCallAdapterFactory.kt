package br.com.concrete.sdk.data.remote.factory

import android.os.AsyncTask
import br.com.concrete.sdk.data.ResponseLiveData
import br.com.concrete.sdk.extension.nextPage
import br.com.concrete.sdk.extension.toDataResponse
import br.com.concrete.sdk.extension.toErrorResponse
import br.com.concrete.sdk.model.DataResult
import br.com.concrete.sdk.model.Page
import br.com.concrete.sdk.model.type.LOADING
import br.com.concrete.sdk.model.type.SUCCESS
import retrofit2.Call
import retrofit2.CallAdapter
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

internal class LiveDataCallAdapter<RESULT>(private val responseType: Type) : CallAdapter<RESULT, ResponseLiveData<RESULT>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<RESULT>) = object : ResponseLiveData<RESULT>() {
        private val started = AtomicBoolean(false)
        override fun onActive() {
            super.onActive()
            value = null.toDataResponse(LOADING)
            if (started.compareAndSet(false, true)) RequestMaker(this::setValue).execute(call)
        }
    }
}

private class RequestMaker<RESULT>(val onRequestFinish: (DataResult<RESULT>) -> Unit) : AsyncTask<Call<RESULT>, Unit, DataResult<RESULT>>() {

    override fun doInBackground(vararg calls: Call<RESULT>): DataResult<RESULT> {
        try {
            val response = calls[0].execute()
            val data = response.body()
            if (data is Page<*>) data.nextPage = response.nextPage()

            return data.toDataResponse(SUCCESS)
        } catch (error: Exception) {
            return error.toErrorResponse()
        }
    }

    override fun onPostExecute(result: DataResult<RESULT>) = onRequestFinish.invoke(result)
}