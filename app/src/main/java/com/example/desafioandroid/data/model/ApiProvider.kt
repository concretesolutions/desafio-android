package com.example.desafioandroid.data.model

import kotlinx.coroutines.flow.emptyFlow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiProvider @Inject constructor() {
    var repositoriesList: List<RepositoriesModel> = emptyList()
    var pullList: List<PullModel> = emptyList()
    lateinit var repository: RepoModel  //EDU Validar null
}