package br.com.rmso.popularrepositories.ui.repository

import br.com.rmso.popularrepositories.model.RepositoryListCallback
import br.com.rmso.popularrepositories.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryPresenter (val view: IRepository.View) : IRepository.Presenter{

    override fun requestList(page: Int, lastPosition: Int) {
        view.progressBar(true)
        val retrofit = RetrofitAPI()
        val callRespository = retrofit.repositoryService.listRepositories(page)
        callRespository.enqueue(object : Callback<RepositoryListCallback> {
            override fun onResponse(call: Call<RepositoryListCallback>, response: Response<RepositoryListCallback>) {
                val listRepositories = response.body()?.items
                view.progressBar(false)
                view.updateList(listRepositories, lastPosition)
            }

            override fun onFailure(call: Call<RepositoryListCallback>, t: Throwable) {
                view.errorRequest(t.message.toString())
                view.progressBar(false)
            }
        })
    }

}