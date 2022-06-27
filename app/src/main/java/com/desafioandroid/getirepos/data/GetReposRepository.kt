package com.desafioandroid.getirepos.data

class GetReposRepository(private val getReposRepository: GetReposRepository) {
    fun getRepos(page: Int, listener: SearchResponseListener){
        this.getReposRepository.getRepos(page, listener)
    }

    fun getPulls(owner: String, respository: String, listener: PullsResponseListener) {
        this.getReposRepository.getPulls(owner, respository, listener)
    }
}