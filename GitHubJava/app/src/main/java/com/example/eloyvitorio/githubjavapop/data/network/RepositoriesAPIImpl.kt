package com.example.eloyvitorio.githubjavapop.data.network

import com.example.eloyvitorio.githubjavapop.data.model.Repository
import com.example.eloyvitorio.githubjavapop.data.model.ResponseResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesAPIImpl(private val createRetrofit: CreateRetrofit) : RepositoriesAPI {
    override fun getJavaRepositories(page: Int, callBack: CallBackRepo) {
        val apiService = createRetrofit.getApi()
        apiService.javaRepositories(page = page)
                .enqueue(object : Callback<ResponseResult> {
                    override fun onResponse(call: Call<ResponseResult>, response: Response<ResponseResult>) {
                        if (response.isSuccessful) {
                            val repos = response.body()?.repositories
                            callBack.onSucessGetRepository(repos!!)
                        }
                    }

                    override fun onFailure(call: Call<ResponseResult>, t: Throwable) {
                        callBack.onErrorGetRepository(
                                "Um erro ocorreu. \nTente carregar novamente.")
                    }
                })
    }
}

interface RepositoriesAPI {
    fun getJavaRepositories( page: Int, callBack: CallBackRepo)
}

interface CallBackRepo {
    fun onSucessGetRepository(repositories: List<Repository>)
    fun onErrorGetRepository(message: String)
}