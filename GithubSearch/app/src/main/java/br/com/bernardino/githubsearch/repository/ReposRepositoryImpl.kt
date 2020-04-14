package br.com.bernardino.githubsearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.bernardino.githubsearch.database.*
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
    var repos: DataSource.Factory<Int, RepositoryDatabase>  =  dao.getRepositories()
    lateinit var boundaryCallback : ReposBoundaryCallback

    override fun refreshRepositories(): RepoSearchResult {
        // Construct the boundary callback
        boundaryCallback = ReposBoundaryCallback(api, dao)
        val networkErrors = boundaryCallback.networkErrors

        // Get data from the local cache
        val data = LivePagedListBuilder(repos, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return RepoSearchResult(data, networkErrors)
    }

    override suspend fun getPullRequest(creator: String, repository: String): List<PullRequest> {
        withContext(Dispatchers.IO) {
            pullRequestList = api.getPullRequests(creator, repository)
        }
        return pullRequestList
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 50;
    }
}