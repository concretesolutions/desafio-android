package br.com.andreyneto.desafioandroid.ui.repos

import br.com.andreyneto.desafioandroid.model.RepoResponse
import br.com.andreyneto.desafioandroid.service.ApiService
import retrofit2.Call
import retrofit2.Response

class ReposPresenter(
        val view: ReposContract.View
): ReposContract.Presenter, retrofit2.Callback<RepoResponse> {

    private val apiService = ApiService()

    init {
        view.setPresenter(this)
    }

    override fun start() {
        getData()
    }

    override fun getData(page: Int) {
        apiService.getApi().repos(page).enqueue(this)
    }

    override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
        if(response.isSuccessful) view.showData(response.body()!!.items)
    }
}