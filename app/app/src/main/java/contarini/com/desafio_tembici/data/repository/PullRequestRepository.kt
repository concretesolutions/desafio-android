package contarini.com.desafio_tembici.data.repository

import contarini.com.desafio_tembici.data.datasource.PullRequestRemoteDataSource

object PullRequestRepository {

    fun getPullRequests(creator : String, repository : String) = PullRequestRemoteDataSource.getPullRequests(creator, repository)

}