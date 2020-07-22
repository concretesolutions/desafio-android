package com.jsouza.repocatalog.data.mapper

import com.jsouza.repocatalog.data.local.entity.RepoKeysEntity
import com.jsouza.repocatalog.data.remote.response.Repository

class KeysMapper {

    companion object {
        fun keysToDatabaseModel(
            repos: List<Repository>,
            prevKey: Int?,
            nextKey: Int?
        ): List<RepoKeysEntity> {
            return repos.map {
                RepoKeysEntity(
                    repositoryId = it.id,
                    previousKey = prevKey,
                    nextKey = nextKey
                )
            }
        }
    }
}
