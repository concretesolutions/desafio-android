package com.uharris.desafio_android.data.base

import kotlinx.coroutines.Deferred
import retrofit2.Response

open class BaseRepository {

    suspend fun <T: Any> safeApiCall(call: Deferred<Response<T>>, default: T): Result<T> {
        return try {
            val response = call.await()
            when(response.isSuccessful) {
                true -> Result.Success(response.body() ?: default)
                false -> Result.Error(Failure.ServerError)
            }
        }catch (e: Throwable){
            Result.Error(Failure.ServerError)
        }
    }
}