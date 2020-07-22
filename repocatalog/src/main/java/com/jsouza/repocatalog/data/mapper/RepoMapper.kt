package com.jsouza.repocatalog.data.mapper

import com.jsouza.repocatalog.data.local.entity.RepositoryEntity
import com.jsouza.repocatalog.data.remote.response.RepositoryResponse
import com.jsouza.repocatalog.utils.RepoTypeConverter

class RepoMapper {

    companion object {
        fun toDatabaseModel(
            response: List<RepositoryResponse>
        ): List<RepositoryEntity> {
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

        fun toDomainModel(
            response: RepositoryEntity
        ): RepositoryResponse {
            return RepositoryResponse(
                id = response.id,
                name = response.name,
                owner = RepoTypeConverter.toOwner(response.owner),
                fullName = response.fullName,
                description = response.description,
                stargazersCount = response.stargazersCount,
                forksCount = response.forksCount,
                pageNumber = response.pageNumber)
        }
    }
}
