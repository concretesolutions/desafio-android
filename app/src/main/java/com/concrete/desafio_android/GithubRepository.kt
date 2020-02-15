package com.concrete.desafio_android

import retrofit2.Retrofit
import com.concrete.desafio_android.BuildConfig.BASE_URL
import com.concrete.desafio_android.domain.Repository
import com.concrete.desafio_android.domain.RepositoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class GithubRepository (
    private val repositoriesCallback: RepositoriesContract.Callback
){

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubService::class.java)

    fun listJavaRepositories(pageNumber: Int){
        retrofit.listJavaRepositories("Java", "stars", pageNumber)?.enqueue(object : Callback<RepositoryResponse?>{
                override fun onResponse(
                    call: Call<RepositoryResponse?>,
                    response: Response<RepositoryResponse?>
                ) {
                   if (response.isSuccessful){
                       val repositoryResponse = response.body() as RepositoryResponse
                       repositoriesCallback.onSucces(repositoryResponse.items as ArrayList<Repository>)
                   }else{
                        repositoriesCallback.onError()
                   }
                }
                override fun onFailure(call: Call<RepositoryResponse?>, t: Throwable) {
                    repositoriesCallback.onFailure(t.message!!)
                }

        })
    }


}