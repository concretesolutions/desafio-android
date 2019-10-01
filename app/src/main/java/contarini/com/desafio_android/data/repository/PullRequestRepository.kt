package contarini.com.desafio_android.data.repository

import contarini.com.desafio_android.data.datasource.PullRequestRemoteDataSource


object PullRequestRepository {

    fun getPullRequests(creator : String, repository : String) = PullRequestRemoteDataSource.getPullRequests(creator, repository)

}