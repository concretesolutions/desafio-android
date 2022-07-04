package com.example.desafioandroid.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiProvider @Inject constructor() {
    var repositoriesList: List<RepoModel> = emptyList()
    var pullList: List<PullModel> = emptyList()
}