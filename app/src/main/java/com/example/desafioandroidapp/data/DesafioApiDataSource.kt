package com.example.desafioandroidapp.data

import com.example.desafioandroidapp.data.dto.Repository
import com.example.desafioandroidapp.data.dto.RepositoryError
import com.example.desafioandroidapp.data.util.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DesafioApiDataSource {
    fun getRepositories(listener: ResponseListener<Repository>, page: Int) {
        val service = RetrofitService.instance.create(DesafioApiService::class.java).getRepositories(page)

        service.enqueue(object : Callback<Repository> {
            override fun onResponse(
                call: Call<Repository>,
                response: Response<Repository>
            ) {
                val callResponse = response.body()
                if (response.isSuccessful && null != callResponse) {
                    listener.onResponse(callResponse)
                } else {
                    listener.onError(
                        RepositoryError(
                            "Unexpected Error",
                            response.code()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<Repository>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        "Unexpected Error",
                        0
                    )
                )
            }

        })
    }
}