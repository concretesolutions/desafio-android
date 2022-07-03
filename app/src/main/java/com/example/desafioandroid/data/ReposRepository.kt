package com.example.desafioandroid.data

import com.example.desafioandroid.data.database.dao.RepoDao
import com.example.desafioandroid.data.database.entities.RepoEntity
import com.example.desafioandroid.data.model.ApiProvider
import com.example.desafioandroid.data.model.RepoModel
import com.example.desafioandroid.data.network.ApiService
import com.example.desafioandroid.domain.model.Repo
import com.example.desafioandroid.domain.model.toDomain
import javax.inject.Inject

class ReposRepository @Inject constructor(
    private val api: ApiService,
    private val repoDao: RepoDao
) {

    suspend fun searchRepositoriesApi(query: String, page: Int): List<Repo> {
        val response = api.searchRepositories(query, page)
        return response.items.map { it.toDomain() }
    }

    //EU Prueba insercion de Datos.
    suspend fun searchRepositoriesDb(): List<Repo> {
        val response = repoDao.getAllRepos()
        return response.map { it.toDomain() }
    }

    suspend fun insertRepos(repo: List<RepoEntity>) {
        repoDao.insertRepos(repo)
    }

    suspend fun deleteAllRepos() {
        repoDao.deleteAllRepos()
    }
}