package com.jsouza.repopullrequests.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jsouza.repopullrequests.data.local.dao.PullsDao
import com.jsouza.repopullrequests.data.local.entity.PullsEntity
import com.jsouza.repopullrequests.data.mapper.PullsMapper
import com.jsouza.repopullrequests.data.remote.RepoDetailService
import com.jsouza.repopullrequests.data.remote.response.OwnerResponse
import com.jsouza.repopullrequests.data.remote.response.PullsResponse
import com.jsouza.repopullrequests.domain.model.PullRequests
import com.jsouza.repopullrequests.domain.repository.PullsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PullsRepositoryImpl(
    private val service: RepoDetailService,
    private val dao: PullsDao
) : PullsRepository {

    private var pullsList = listOf<PullsResponse>()
    private var repositoryId = 0L

    override fun getPullRequests(
        repositoryId: Long
    ): LiveData<List<PullRequests>?> {
        return Transformations.map(dao.getPullRequests(repositoryId)) {
            PullsMapper.toDomainModel(it)
        }
    }

    override suspend fun refreshPullRequests(
        userName: String,
        repoName: String,
        repositoryId: Long
    ) {
        this.repositoryId = repositoryId

        withContext(Dispatchers.IO) {
            try {
                pullsList = service.loadPullsPageFromApiAsync(userName, repoName)
                val responseIsEmpty = pullsList.isEmpty()

                if (responseIsEmpty) {
                    val pullRequestList = dao.getPullRequestsAsMutableList(repositoryId)

                    insertEmptyPullRequestOnDatabase(pullRequestList)
                } else {
                    assignRepositoryIdToEachPullRequest(pullsList)

                    pullsList.let { list ->
                        PullsMapper.toDatabaseModel(list)?.let {
                            dao.insertAll(*it)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.i("Api Error", "${e.message}")
            }
        }
    }

    private suspend fun insertEmptyPullRequestOnDatabase(
        pullRequestList: MutableList<PullsEntity>
    ) {
        if (pullRequestList.isEmpty()) {
            val pullRequest = PullRequests(0,
                "http://github.com",
                "",
                "This Repository Does not have Pull Requests",
                OwnerResponse("", ""),
                "",
                "",
                repositoryId)

            pullRequestList.add(PullsMapper.toDomainModelPlainObject(pullRequest))
            dao.insertAll(*pullRequestList.toTypedArray())
        }
    }

    private fun assignRepositoryIdToEachPullRequest(
        pullsList: List<PullsResponse>
    ) {
        pullsList.forEach {
            it.repositoryId = repositoryId
        }
    }
}
