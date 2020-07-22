package com.jsouza.repocatalog.data.mapper

import com.jsouza.repocatalog.data.local.entity.RepositoryEntity
import com.jsouza.repocatalog.data.remote.response.RepositoryResponse
import com.jsouza.repocatalog.utils.RepoTypeConverter

class RepoMapper {

    companion object {
        fun toDatabaseModel(
            response: List<RepositoryResponse>
        ): List<RepositoryEntity> {
            return response.map { repositoryResponse ->
                RepositoryEntity(
                id = repositoryResponse.id,
                name = repositoryResponse.name,
                owner = RepoTypeConverter.fromOwner(repositoryResponse.owner),
                fullName = repositoryResponse.fullName,
                description = repositoryResponse.description,
                stargazersCount = repositoryResponse.stargazersCount,
                forksCount = repositoryResponse.forksCount,
                pageNumber = repositoryResponse.pageNumber)
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
