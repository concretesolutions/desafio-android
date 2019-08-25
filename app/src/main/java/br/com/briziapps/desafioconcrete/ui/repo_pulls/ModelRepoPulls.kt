package br.com.briziapps.desafioconcrete.ui.repo_pulls

import android.util.Log
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoPullObjects
import br.com.briziapps.desafioconcrete.mvp.Mvp
import br.com.briziapps.desafioconcrete.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelRepoPulls (private val presenterRepoPulls: PresenterRepoPulls): Mvp.ModelRepoPulls{

    private val tag = "ModelRepoPulls"

    override fun getRepoPullsOnApi(repoName: String, page: String) {

        Client.getApi().getRepoPullsOnApi( repoName, page, "open").enqueue( object : Callback<List<GitHubRepoPullObjects>>{

            override fun onResponse( call: Call<List<GitHubRepoPullObjects>>, response: Response<List<GitHubRepoPullObjects>> ) {

                if (response.isSuccessful){

                    val repoPullObjects:List<GitHubRepoPullObjects>? = response.body()

                    if (repoPullObjects != null){
                        presenterRepoPulls.updateRecyclerViewRepoPulls(repoPullObjects)
                        presenterRepoPulls.hideProgressBar()
                    }

                }else{
                    Log.d(tag, "!response.isSuccessful message =  ${response.raw()}")
                    presenterRepoPulls.hideProgressBar()
                }

            }

            override fun onFailure(call: Call<List<GitHubRepoPullObjects>>, t: Throwable) {
                Log.e(tag, "Error Throwable : ", t)
                presenterRepoPulls.hideProgressBar()
            }

        })
    }

}