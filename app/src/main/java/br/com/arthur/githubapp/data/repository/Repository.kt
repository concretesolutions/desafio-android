package br.com.arthur.githubapp.data.repository

import android.content.Context
import br.com.arthur.githubapp.data.service.Service
import br.com.arthur.githubapp.data.service.ServiceFactory
import br.com.arthur.githubapp.model.GitRepository
import br.com.arthur.githubapp.model.PullRequest

class Repository(context: Context) : BaseRepository(context) {

    private val service: Service = ServiceFactory.getService()

    companion object {
        const val CONNECTION_MESSAGE = "Sem conexão com a internet"
    }

    suspend fun getRepositories(page: Int, success: (List<GitRepository>) -> Unit, failed: (String) -> Unit) {
        if (checkConnection()) {
            val response = service.getRepositoriesAsync("Java", "stars", page).await()
            when {
                response.isSuccessful -> response.body().let { repositories ->
                    repositories?.items?.let { list -> success(list) }
                }
                else -> failed("Não foi possível obter a lista de repositorios")
            }
        } else {
            failed(CONNECTION_MESSAGE)
        }
    }

    suspend fun getPulls(creator: String, repositoryName: String, success: (List<PullRequest>) -> Unit, failed: (String) -> Unit) {
        if (checkConnection()) {
            val response = service.getPullRequestsRepositoryAsync(creator, repositoryName).await()
            when {
                response.isSuccessful -> response.body().let {pull -> pull?.let { success(it) } }
                else -> failed("Não foi possível obter a lista de Pull Request")
            }
        } else {
            failed(CONNECTION_MESSAGE)
        }
    }

}