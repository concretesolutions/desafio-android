package contarini.com.desafio_android.data.datasource

import contarini.com.desafio_android.NetworkConstants
import contarini.com.desafio_android.data.ServiceGenerator
import contarini.com.desafio_android.data.interceptors.UnauthorisedInterceptor
import contarini.com.desafio_android.data.service.PullRequestService


object PullRequestRemoteDataSource {

    private var mService = ServiceGenerator.createService(
        interceptors = listOf(UnauthorisedInterceptor()),
        serviceClass = PullRequestService::class.java,
        url = NetworkConstants.SEARCH_PULL_REQUESTS
    )

    fun getPullRequests(creator : String, repository : String) = mService.getIncidents(creator, repository)


}