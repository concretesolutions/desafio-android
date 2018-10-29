package br.com.andreyneto.desafioandroid.ui.repos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.andreyneto.desafioandroid.model.Repo
import br.com.andreyneto.desafioandroid.model.RepoResponse
import br.com.andreyneto.desafioandroid.service.ApiService
import br.com.andreyneto.desafioandroid.ui.pulls.PullsActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response
import java.io.Serializable

class ReposPresenter(
        val view: ReposContract.View
): ReposContract.Presenter, retrofit2.Callback<RepoResponse> {
    override fun openRepo(context: Context, repo: Repo) {
        val i = Intent(context, PullsActivity::class.java)
        i.putExtra("repo",Gson().toJson(repo))
        context.startActivity(i)
    }

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

    }

    override fun onResponse(call: Call<RepoResponse>, response: Response<RepoResponse>) {
        if(response.isSuccessful) view.showData(response.body()!!.items)
    }
}
