package contarini.com.desafio_android.data.repository

import contarini.com.desafio_android.data.datasource.RepositoriesRemoteDataSource

object RepositoriesRepository {

    fun getIncidents(language : String, sort : String, page : Int) = RepositoriesRemoteDataSource.getIncidents(language, sort, page)
}