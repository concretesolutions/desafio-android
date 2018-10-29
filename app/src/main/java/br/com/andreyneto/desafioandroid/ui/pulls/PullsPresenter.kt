package br.com.andreyneto.desafioandroid.ui.pulls

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import br.com.andreyneto.desafioandroid.R
import br.com.andreyneto.desafioandroid.model.Pull
import br.com.andreyneto.desafioandroid.model.Repo
import br.com.andreyneto.desafioandroid.service.ApiService
import retrofit2.Call
import retrofit2.Response

class PullsPresenter(
        val view: PullsContract.View,
        val repo: Repo
): PullsContract.Presenter, retrofit2.Callback<List<Pull>> {
    private val apiService = ApiService()

    init {
        view.setPresenter(this)
    }

    override fun getData(owner: String, repo: String) {
        apiService.getApi().pulls(owner, repo).enqueue(this)
    }

    override fun start() {
        getData(repo.owner.name, repo.name)
    }

    override fun onFailure(call: Call<List<Pull>>, t: Throwable) {

    }

    override fun onResponse(call: Call<List<Pull>>, response: Response<List<Pull>>) {
        if(response.isSuccessful) view.showData(response.body()!!)
    }
    override fun openPull(url: String, ctx: Context) {
        val uri = Uri.parse(url)
        val customTabsIntentBuilder = CustomTabsIntent.Builder(null)
        customTabsIntentBuilder.setToolbarColor(ContextCompat.getColor(ctx, R.color.colorPrimary));
        val customTabsIntent = customTabsIntentBuilder.build()
        customTabsIntent.launchUrl(ctx, uri)
    }


}