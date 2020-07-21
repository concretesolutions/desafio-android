package com.jsouza.repocatalog.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jsouza.repocatalog.data.local.RepoDatabase
import com.jsouza.repocatalog.data.local.entity.RepoKeysEntity
import com.jsouza.repocatalog.data.local.entity.RepositoryEntity
import com.jsouza.repocatalog.data.mapper.RepoMapper
import com.jsouza.repocatalog.data.remote.RepoCatalogService
import com.jsouza.repocatalog.data.remote.requeststatus.RequestStatus
import com.jsouza.repocatalog.data.remote.response.Repository
import java.io.IOException
import retrofit2.HttpException

@ExperimentalPagingApi
class RepoMediator(
    private val service: RepoCatalogService,
    private val database: RepoDatabase
) : RemoteMediator<Int, RepositoryEntity>() {

    companion object {
        private const val FIRST_PAGE = 0
        private const val SINGLE_PAGE = 1
    }

    private var actualPage = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepositoryEntity>
    ): MediatorResult {

        actualPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(SINGLE_PAGE) ?: FIRST_PAGE
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state) ?: throw RequestStatus.NullKeysError

                remoteKeys.previousKey ?: return MediatorResult.Success(endOfPaginationReached = true)

                remoteKeys.previousKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys?.nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                remoteKeys.nextKey
            }
        }

        return try {
            val reposList = fetchDataFromApi()

            val isPaginationOnEnd = reposList?.isEmpty() ?: true

            saveDataOnDatabase(loadType, isPaginationOnEnd, reposList)

            MediatorResult.Success(endOfPaginationReached = isPaginationOnEnd)
        } catch (exception: IOException) {
            MediatorResult.Error(RequestStatus.LoadError)
        } catch (exception: HttpException) {
            MediatorResult.Error(RequestStatus.ApiError)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, RepositoryEntity>
    ): RepoKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                database.keysDao().getRemoteKey(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, RepositoryEntity>
    ): RepoKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                database.keysDao().getRemoteKey(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, RepositoryEntity>
    ): RepoKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                database.keysDao().getRemoteKey(repoId)
            }
        }
    }

    private suspend fun fetchDataFromApi(): List<Repository>? {
        val repositoriesResponse = service
            .loadRepositoryPageFromApiAsync(
                actualPage)

        return repositoriesResponse.items
    }

    private suspend fun saveDataOnDatabase(
        loadType: LoadType,
        endOfPaginationReached: Boolean,
        repos: List<Repository>?
    ) {
        database.withTransaction {
            clearDatabaseIfIsOnRefreshingState(loadType)

            val prevKey = if (actualPage == FIRST_PAGE) null else actualPage - SINGLE_PAGE
            val nextKey = if (endOfPaginationReached) null else actualPage + SINGLE_PAGE

            val keys = keysToDomainModel(repos, prevKey, nextKey)

            insertDataOnDatabase(keys, repos)
        }
    }

    private suspend fun clearDatabaseIfIsOnRefreshingState(
        loadType: LoadType
    ) {
        if (loadType == LoadType.REFRESH) {
            database.keysDao().clearRemoteKeys()
            database.reposDao().clearRepos()
        }
    }

    private fun keysToDomainModel(
        repos: List<Repository>?,
        prevKey: Int?,
        nextKey: Int?
    ): List<RepoKeysEntity>? {
        return repos?.map {
            RepoKeysEntity(
                repositoryId = it.id,
                previousKey = prevKey,
                nextKey = nextKey
            )
        }
    }

    private suspend fun insertDataOnDatabase(
        keys: List<RepoKeysEntity>?,
        repos: List<Repository>?
    ) {
        val resultList = repos?.let { RepoMapper.toDatabaseModel(it) }
        keys?.let { database.keysDao().insertAll(it) }
        resultList?.let { database.reposDao().insertAll(it) }
    }
}
