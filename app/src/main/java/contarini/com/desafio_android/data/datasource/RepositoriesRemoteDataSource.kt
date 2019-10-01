package contarini.com.desafio_android.data.datasource

import contarini.com.desafio_android.NetworkConstants
import contarini.com.desafio_android.data.ServiceGenerator
import contarini.com.desafio_android.data.interceptors.UnauthorisedInterceptor
import contarini.com.desafio_android.data.service.RepositoryService


object RepositoriesRemoteDataSource {

    private var mService = ServiceGenerator.createService(
        interceptors = listOf(UnauthorisedInterceptor()),
        serviceClass = RepositoryService::class.java,
        url = NetworkConstants.SEARCH_REPOSITORIES
    )

    fun getIncidents(language : String, sort : String, page : Int) = mService.getIncidents(language, sort, page)

}