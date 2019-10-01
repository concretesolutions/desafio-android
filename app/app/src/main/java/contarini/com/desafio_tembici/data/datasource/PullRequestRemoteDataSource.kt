package contarini.com.desafio_tembici.data.datasource

import contarini.com.desafio_tembici.NetworkConstants
import contarini.com.desafio_tembici.data.ServiceGenerator
import contarini.com.desafio_tembici.data.interceptors.UnauthorisedInterceptor
import contarini.com.desafio_tembici.data.service.PullRequestService


object PullRequestRemoteDataSource {

    private var mService = ServiceGenerator.createService(
        interceptors = listOf(UnauthorisedInterceptor()),
        serviceClass = PullRequestService::class.java,
        url = NetworkConstants.SEARCH_PULL_REQUESTS
    )

    fun getPullRequests(creator : String, repository : String) = mService.getIncidents(creator, repository)


}