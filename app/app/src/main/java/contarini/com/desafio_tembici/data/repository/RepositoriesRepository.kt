package contarini.com.desafio_tembici.data.repository

import contarini.com.desafio_tembici.data.datasource.RepositoriesRemoteDataSource


object RepositoriesRepository {

    fun getIncidents(language : String, sort : String, page : Int) = RepositoriesRemoteDataSource.getIncidents(language, sort, page)
}