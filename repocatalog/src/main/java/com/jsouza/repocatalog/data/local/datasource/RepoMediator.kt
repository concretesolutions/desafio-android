package com.jsouza.repocatalog.data.local.datasource

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
import java.io.IOException
import java.io.InvalidObjectException
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

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepositoryEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(SINGLE_PAGE) ?: FIRST_PAGE
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                    ?: throw InvalidObjectException("Remote key and the prevKey should not be null")

                remoteKeys.previousKey ?: return MediatorResult.Success(
                    endOfPaginationReached = true)

                remoteKeys.previousKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys?.nextKey == null) {
                    throw InvalidObjectException("Remote key should not be null for $loadType")
                }
                remoteKeys.nextKey
            }
        }

        try {
            val apiResponse = service.loadRepositoryPageFromApiAsync(
                page,
                state.config.pageSize)

            val repos = apiResponse.items
            val endOfPaginationReached = repos?.isEmpty() ?: true

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.keysDao().clearRemoteKeys()
                    database.reposDao().clearRepos()
                }
                val prevKey = if (page == FIRST_PAGE) null else page - SINGLE_PAGE
                val nextKey = if (endOfPaginationReached) null else page + SINGLE_PAGE
                val keys = repos?.map {
                    RepoKeysEntity(
                        repositoryId = it.id,
                        previousKey = prevKey,
                        nextKey = nextKey
                    )
                }
                val resultList = repos?.let { RepoMapper.toDatabaseModel(it) }
                keys?.let { database.keysDao().insertAll(it) }
                resultList?.let { database.reposDao().insertAll(it) }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
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
}
