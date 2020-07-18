package com.jsouza.repocatalog.data.local.mapper

import com.jsouza.repocatalog.data.local.entity.RepositoryEntity
import com.jsouza.repocatalog.data.local.utils.RepoTypeConverter
import com.jsouza.repocatalog.data.repocatalog.remote.response.Repository

class RepoMapper {

    companion object {
        fun toDatabaseModel(response: List<Repository>): List<RepositoryEntity> {
            return response.map { RepositoryEntity(
                id = it.id,
                name = it.name,
                owner = RepoTypeConverter.fromOwner(it.owner),
                fullName = it.fullName,
                description = it.description,
                stargazersCount = it.stargazersCount,
                forksCount = it.forksCount,
                pageNumber = it.pageNumber)
            }
        }
    }
}
