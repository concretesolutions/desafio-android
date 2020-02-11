package br.com.bernardino.githubsearch.repository

import androidx.lifecycle.MutableLiveData
import br.com.bernardino.githubsearch.api.RetrofitInitializer
import br.com.bernardino.githubsearch.model.Repository
import br.com.bernardino.githubsearch.model.RepositoryBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object ReposRepository {

    fun getRepositories(
        successHandler: (List<Repository>?) -> Unit,
        failureHandler: (Throwable?) -> Unit
    ) {
        val call = RetrofitInitializer().reposService()
            .getRepositories("language:Java", "stars", 1)

        call.enqueue(object : Callback<RepositoryBody?> {
            override fun onResponse(
                call: Call<RepositoryBody?>,
                response: Response<RepositoryBody?>
            ) {
                if (response?.code() == 200) {
                    response.body()?.let {
                        successHandler(it.items)
                    }
                }
            }

            override fun onFailure(call: Call<RepositoryBody?>, t: Throwable) {
                failureHandler(t)
            }
        }

        )
    }

}