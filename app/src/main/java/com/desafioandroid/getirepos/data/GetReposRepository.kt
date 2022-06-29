package com.desafioandroid.getirepos.data

class GetReposRepository(private val getReposDatasource: GetReposDatasource) {
    fun getRepos(page: Int, listener: SearchResponseListener){
        this.getReposDatasource.getRepos(page, listener)
    }

    fun getPulls(owner: String, respository: String, listener: PullsResponseListener) {
        this.getReposDatasource.getPulls(owner, respository, listener)
    }
}