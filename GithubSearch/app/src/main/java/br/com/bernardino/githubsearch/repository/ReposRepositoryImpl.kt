package br.com.bernardino.githubsearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.bernardino.githubsearch.database.ReposDao
import br.com.bernardino.githubsearch.database.RepositoriesDatabase
import br.com.bernardino.githubsearch.database.RepositoryDatabase
import br.com.bernardino.githubsearch.database.asDomainModel
import br.com.bernardino.githubsearch.model.PullRequest
import br.com.bernardino.githubsearch.network.GithubApi
import br.com.bernardino.githubsearch.network.RetrofitInitializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import retrofit2.await

@ExperimentalCoroutinesApi
class ReposRepositoryImpl(private val dao: ReposDao, private val api: GithubApi) : ReposRepository,
    KoinComponent {

    private lateinit var pullRequestList: List<PullRequest>
    var repos: LiveData<List<RepositoryDatabase>> =  dao.getRepositories()

    override suspend fun refreshRepositories(page: Int) {
        withContext(Dispatchers.IO) {
            val reposList = api.getRepositories(page)
            dao.insertAll(reposList.items.asDomainModel())
        }
    }

    override suspend fun getPullRequest(creator: String, repository: String): List<PullRequest> {
        withContext(Dispatchers.IO) {
            pullRequestList = api.getPullRequests(creator, repository)
        }

        return pullRequestList
    }
}