package br.com.concrete.desafio.data.remote.factory

import br.com.concrete.desafio.data.extension.loadingResponse
import br.com.concrete.desafio.data.extension.toDataResponse
import br.com.concrete.desafio.data.extension.toErrorResponse
import br.com.concrete.desafio.data.livedata.ResponseLiveData
import br.com.concrete.desafio.data.model.enum.DataResultStatus.SUCCESS
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

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

    override fun adapt(call: Call<RESULT>): ResponseLiveData<RESULT> {
        return object : ResponseLiveData<RESULT>() {
            override fun abort() {
                call.cancel()
            }

            @Throws(Exception::class)
            override fun compute() {
                try {
                    postValue(loadingResponse())
                    postValue(makeRequest(call).toDataResponse(SUCCESS))
                } catch (error: Exception) {
                    postValue(error.toErrorResponse())
                    throw error
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun <T> makeRequest(call: Call<T>): T? {
        return if (call.isExecuted)
            call.clone().execute().body()
        else
            call.execute().body()
    }
}