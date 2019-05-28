package com.losingtimeapps.presentation.mapper

import com.losingtimeapps.domain.entity.Repository
import com.losingtimeapps.presentation.model.AuthorModel
import com.losingtimeapps.presentation.model.RepositoryModel

class RepositoryModelMapper {

    fun apply(repositoryList: List<Repository>): List<RepositoryModel> {

        return repositoryList.map {
            RepositoryModel(
                it.id, it.name, it.description,
                it.starsAmount.toString(),
                it.forksAmount.toString(),
                AuthorModel(
                    it.author.id,
                    it.author.name,
                    it.author.photoUrl
                )
            )
        }
    }
}