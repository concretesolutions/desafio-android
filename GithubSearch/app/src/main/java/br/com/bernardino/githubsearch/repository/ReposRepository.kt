package br.com.bernardino.githubsearch.repository

import androidx.lifecycle.LiveData
import br.com.bernardino.githubsearch.database.RepositoriesDatabase
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.database.asDomainModel
import br.com.bernardino.githubsearch.network.RetrofitInitializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await


class ReposRepository(private val database: RepositoriesDatabase) {

    val repos: LiveData<List<RepositoryDatabase>> = database.reposDao.getRepositories()

    suspend fun refreshRepositories() {
        withContext(Dispatchers.IO) {
            val reposlist = RetrofitInitializer().reposService().getRepositories("language:Java", "stars", 1)
                    .await()
            database.reposDao.insertAll(reposlist.items.asDomainModel())
        }
    }
}