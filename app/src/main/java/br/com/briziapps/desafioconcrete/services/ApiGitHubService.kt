package br.com.briziapps.desafioconcrete.services

import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepo
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoPullObjects
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGitHubService {


    @GET("search/repositories")
    fun getReposOnApi( @Query("q")  q:String, @Query("sort") sort:String, @Query("page") page:String ) : Call<GitHubRepo>

    @GET("repos/{repo_name}/pulls")
    fun getRepoPullsOnApi( @Path("repo_name", encoded = true) repoName: String, @Query("page") page:String, @Query("state")  state:String ) : Call<List<GitHubRepoPullObjects>>


}