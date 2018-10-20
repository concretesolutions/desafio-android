package cl.mauledev.github.data.datasources.remote.api

import cl.mauledev.github.data.model.PullRequest
import cl.mauledev.github.data.model.Repo
import cl.mauledev.github.data.model.Response
import cl.mauledev.github.data.model.User
import io.reactivex.Flowable
import javax.inject.Inject

class RepoAPI @Inject constructor(var api: AppService) {

    fun getRepos(page: Int): Flowable<Response> {
        return api.getRepos(page = page)
    }

    fun getPullRequests(user: String, repoTitle: String): Flowable<List<PullRequest>> {
        return api.getPullRequests(user, repoTitle)
    }
}