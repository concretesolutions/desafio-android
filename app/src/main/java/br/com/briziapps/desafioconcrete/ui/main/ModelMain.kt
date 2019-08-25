package br.com.briziapps.desafioconcrete.ui.main

import android.util.Log
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepo
import br.com.briziapps.desafioconcrete.mvp.Mvp
import br.com.briziapps.desafioconcrete.services.Client
//import br.com.briziapps.desafioconcrete.services.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelMain (private val presenterMain: PresenterMain): Mvp.ModelMain{

    private val tag = "ModelMain"

    override fun getReposOnApi(search: String, page: String) {

        Client.getApi().getReposOnApi( "language:$search", "stars", page ).enqueue( object : Callback<GitHubRepo>{

         override fun onResponse( call: Call<GitHubRepo>, response: Response<GitHubRepo>) {

             if (response.isSuccessful){

                 val repositories:GitHubRepo? = response.body()
                 if (repositories != null)
                     presenterMain.updateRecyclerViewRepos(repositories.items)
                 else{
                     Log.i(tag, "objects came null null")
                     presenterMain.hideProgressBar()
                 }
             }else{
                 Log.i(tag, "!response.isSuccessful = ${response.message()}")
                 presenterMain.hideProgressBar()
             }
         }

         override fun onFailure(call: Call<GitHubRepo>, t: Throwable) {
             Log.e(tag, "Error Throwable : ", t)
             presenterMain.hideProgressBar()
         }

     })

    }

}