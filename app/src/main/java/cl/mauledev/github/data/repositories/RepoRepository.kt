package cl.mauledev.github.data.repositories

import cl.mauledev.github.data.datasources.remote.RemoteDataSource
import cl.mauledev.github.data.model.PullRequest
import cl.mauledev.github.data.model.Repo
import cl.mauledev.github.data.model.Response
import io.reactivex.Flowable
import javax.inject.Inject

class RepoRepository @Inject constructor(var remoteDataSource: RemoteDataSource) {

    fun getRepos(page: Int): Flowable<Response> {
        return remoteDataSource.getRepos(page)
    }

    fun getPullRequests(repo: Repo): Flowable<List<PullRequest>> {
        repo.author?.login ?: return Flowable.error(NullPointerException())
        repo.name ?: return Flowable.error(NullPointerException())

        return remoteDataSource.getPullRequests(repo.author?.login!!, repo.name!!)
    }
}