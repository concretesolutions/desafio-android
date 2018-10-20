package cl.mauledev.github.data.datasources.remote

import cl.mauledev.github.data.datasources.remote.api.RepoAPI
import cl.mauledev.github.data.model.PullRequest
import cl.mauledev.github.data.model.Response
import io.reactivex.Flowable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private var api: RepoAPI) {

    fun getRepos(page: Int): Flowable<Response> {
        return api.getRepos(page)
    }

    fun getPullRequests(user: String, repoTitle: String): Flowable<List<PullRequest>> {
        return api.getPullRequests(user, repoTitle)
    }
}