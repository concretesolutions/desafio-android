package com.example.challengecskotlin.models

import com.example.challengecskotlin.models.Repo

class SearchResponse {
    val items: List<Repo> = ArrayList()

    fun getRepos(): List<Repo> {
        return items
    }
}