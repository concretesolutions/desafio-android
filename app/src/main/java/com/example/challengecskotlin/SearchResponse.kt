package com.example.challengecskotlin

class SearchResponse {
    val items: List<Repo> = ArrayList<Repo>()

    fun getRepos(): List<Repo> {
        return items
    }
}