package contarini.com.desafio_tembici.data.datasource

import contarini.com.desafio_tembici.NetworkConstants
import contarini.com.desafio_tembici.data.service.RepositoryService
import contarini.com.desafio_tembici.data.ServiceGenerator
import contarini.com.desafio_tembici.data.interceptors.UnauthorisedInterceptor


object RepositoriesRemoteDataSource {

    private var mService = ServiceGenerator.createService(
        interceptors = listOf(UnauthorisedInterceptor()),
        serviceClass = RepositoryService::class.java,
        url = NetworkConstants.SEARCH_REPOSITORIES
    )

    fun getIncidents(language : String, sort : String, page : Int) = mService.getIncidents(language, sort, page)

}