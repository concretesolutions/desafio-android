package com.jsouza.repocatalog.data.repocatalog.paging.local.datasource

import android.util.Log
import androidx.paging.PagingSource
import com.jsouza.repocatalog.data.repocatalog.remote.RepositoryCatalogService
import com.jsouza.repocatalog.data.repocatalog.remote.response.Repository
import retrofit2.HttpException

class RepositoryDataSource(
    private val service: RepositoryCatalogService
) : PagingSource<Int, Repository>() {

    companion object {
        private const val FIRST_PAGE = 0
        private const val SINGLE_PAGE = 1
        private const val RESULTS_PER_PAGE = 20
        private const val QUERY = "language:Java"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val nextPageNumber = params.key ?: FIRST_PAGE
            val reposList = service.loadRepositoryPageFromApiAsync(
                QUERY,
                nextPageNumber,
                RESULTS_PER_PAGE)

            LoadResult.Page(
                data = reposList.items,
                prevKey = if (nextPageNumber == FIRST_PAGE) null else nextPageNumber - SINGLE_PAGE,
                nextKey = if (reposList.items.isEmpty()) null else nextPageNumber + SINGLE_PAGE
            )
        } catch (e: Exception) {
            Log.i("Api DataSource", "Failed to fetch data before ${e.message}")
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.i("Api DataSource", "Http Error ${e.message}")
            return LoadResult.Error(e)
        }
    }
}
