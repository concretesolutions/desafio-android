package com.jsouza.repopullrequests.data.mapper

import com.jsouza.repopullrequests.data.local.entity.PullsEntity
import com.jsouza.repopullrequests.data.remote.response.PullsResponse
import com.jsouza.repopullrequests.domain.model.PullRequests
import com.jsouza.repopullrequests.utils.PullsTypeConverter

class PullsMapper {

    companion object {

        fun toDatabaseModel(
            response: List<PullsResponse>
        ): Array<PullsEntity>? {
            return response.map { responseModel ->
                PullsEntity(
                    id = responseModel.id,
                    url = responseModel.url,
                    title = responseModel.title,
                    owner = PullsTypeConverter.fromOwner(responseModel.owner),
                    body = responseModel.body,
                    createdAt = responseModel.getCreatedAtDateString(),
                    state = responseModel.state,
                    repositoryId = responseModel.repositoryId
                )
            }.toTypedArray()
        }

        fun toDomainModel(
            entity: List<PullsEntity>?
        ): List<PullRequests>? {
            return entity?.map { entityModel ->
                PullRequests(
                id = entityModel.id,
                url = entityModel.url,
                title = entityModel.title,
                owner = PullsTypeConverter.toOwner(entityModel.owner),
                body = entityModel.body,
                createdAt = entityModel.createdAt,
                state = entityModel.state,
                repositoryId = entityModel.repositoryId)
            }
        }

        fun toDomainModelPlainObject(
            domainModel: PullRequests
        ): PullsEntity {
            return PullsEntity(
                id = domainModel.id,
                url = domainModel.url,
                title = domainModel.title,
                owner = PullsTypeConverter.fromOwner(domainModel.owner),
                body = domainModel.body,
                createdAt = domainModel.createdAt,
                state = domainModel.state,
                repositoryId = domainModel.repositoryId)
        }
    }
}
