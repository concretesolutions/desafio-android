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
            return response.map {
                PullsEntity(
                    id = it.id,
                    url = it.url,
                    title = it.title,
                    owner = PullsTypeConverter.fromOwner(it.owner),
                    body = it.body,
                    createdAt = it.getCreatedAtDateString(),
                    state = it.state,
                    repositoryId = it.repositoryId
                )
            }.toTypedArray()
        }

        fun toDomainModel(
            entity: List<PullsEntity>?
        ): List<PullRequests>? {
            return entity?.map { PullRequests(
                id = it.id,
                url = it.url,
                title = it.title,
                owner = PullsTypeConverter.toOwner(it.owner),
                body = it.body,
                createdAt = it.createdAt,
                state = it.state,
                repositoryId = it.repositoryId)
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
