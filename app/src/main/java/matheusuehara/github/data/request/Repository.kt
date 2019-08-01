package matheusuehara.github.data.request

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import matheusuehara.github.data.model.PullRequest
import matheusuehara.github.data.model.RepositoryResponse
import matheusuehara.github.data.network.GitHubService

class Repository(private val gitHubService: GitHubService) : RepositoryContract {

    override fun getRepositories(language: String, sort: String, page: Int): Observable<RepositoryResponse> {
        return gitHubService
                .getRepositories(language, sort, page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }

    override fun getPullRequests(owner: String, repository: String, status: String): Observable<ArrayList<PullRequest>> {
        return gitHubService
                .getPullRequests(owner, repository, status)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())

    }
}