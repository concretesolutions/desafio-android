package matheusuehara.github.data.request

import io.reactivex.Observable
import matheusuehara.github.data.model.PullRequest
import matheusuehara.github.data.model.RepositoryResponse

interface RepositoryContract {
    fun getRepositories(language: String, sort: String, page: Int): Observable<RepositoryResponse>
    fun getPullRequests(owner: String, repository: String, status: String): Observable<ArrayList<PullRequest>>
}
