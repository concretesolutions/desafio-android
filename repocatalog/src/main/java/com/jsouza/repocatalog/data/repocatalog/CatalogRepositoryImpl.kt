package com.jsouza.repocatalog.data.repocatalog

import android.util.Log
import com.jsouza.repocatalog.data.repocatalog.remote.RepositoryCatalogService
import com.jsouza.repocatalog.data.repocatalog.remote.response.RepositoryList
import com.jsouza.repocatalog.domain.repository.CatalogRepository
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogRepositoryImpl(
    private val repositoryCatalogService: RepositoryCatalogService
) : CatalogRepository {

    override suspend fun refreshRepos(page: Int): RepositoryList? {
        var reposList: RepositoryList? = null
        withContext(Dispatchers.IO) {
            try {
                reposList = repositoryCatalogService.loadRepositoryPageFromApi(page).await()
            } catch (e: Exception) {
                Log.i("Api Error", e.message.toString())
            }
        }
        return reposList
    }
}
