package com.example.desafioandroidapp.data

import com.example.desafioandroidapp.data.dto.Repository
import com.example.desafioandroidapp.data.dto.Error
import com.example.desafioandroidapp.data.dto.Pull
import com.example.desafioandroidapp.data.util.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DesafioApiDataSource {
    fun getRepositories(listener: RepositoryListener<Repository>, page: Int) {
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
                        Error(
                            "Unexpected Error",
                            response.code()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<Repository>, t: Throwable) {
                listener.onError(
                    Error(
                        "Unexpected Error",
                        0
                    )
                )
            }

        })
    }

    fun getPulls(listener: PullListener<List<Pull>>, owner: String, repository: String) {
        val service = RetrofitService.instance.create(DesafioApiService::class.java).getPulls(owner,repository)

        service.enqueue(object : Callback<List<Pull>> {
            override fun onResponse(
                call: Call<List<Pull>>,
                response: Response<List<Pull>>
            ) {
                val callResponse = response.body()
                if (response.isSuccessful && null != callResponse) {
                    listener.onResponsePull(callResponse)
                } else {
                    listener.onError(
                        Error(
                            "Unexpected Error",
                            response.code()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<List<Pull>>, t: Throwable) {
                listener.onError(
                    Error(
                        "Unexpected Error",
                        0
                    )
                )
            }

        })
    }
}
