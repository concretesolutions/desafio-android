package br.com.briziapps.desafioconcrete.mvp

import android.content.Context
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoObjects
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoPullObjects

interface Mvp {

    /*interface GitHubServices{
        @GET("search/repositories")
        fun getReposOnApi(@Query("q")  q:String, @Query("sort") sort:String, @Query("page") page:String ) :Call<GitHubRepo>

        @GET("repos/{repo_name}/pulls")
        fun getRepoPullsOnApi(@Path("repo_name", encoded = true) repoName: String, @Query("page") page:String, @Query("state")  state:String ) :Call<List<GitHubRepoPullObjects>>

    }*/

    /*Main Activity*/
    interface PresenterMain{
        fun getContext(context: Context)
        fun getReposOnApi(search: String, page: String)
        fun updateRecyclerViewRepos(repositories: List<GitHubRepoObjects>)
        fun hideProgressBar()
    }
    interface ModelMain{
        //fun retrofitClient(): Retrofit
        fun getReposOnApi(search: String, page: String)
    }
    interface ViewMain{
        fun updateRecyclerViewRepos(repositories: List<GitHubRepoObjects>)
        fun hideProgressBar()
    }
    /*Main Activity*/

    /*Repositorie Pull Activity*/
    interface PresenterRepoPulls{
        fun setContext(context: Context)
        fun getRepoPullsOnApi(repoNaame: String, page: String)
        fun updateRecyclerViewRepoPulls(repositoriePulls: List<GitHubRepoPullObjects>)
        fun hideProgressBar()

    }
    interface ModelRepoPulls{
        //fun retrofitClient(): Retrofit
        fun getRepoPullsOnApi(repoName: String, page: String)

    }
    interface ViewRepoPulls{
        fun updateRecyclerViewRepoPulls(repositoriePulls: List<GitHubRepoPullObjects>)
        fun hideProgressBar()
    }
    /*Repositorie Pull Activity*/

}