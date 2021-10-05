package com.concrete.challenge.domain.io.response

import com.concrete.challenge.data.RepositoryEntity
import com.google.gson.annotations.SerializedName

class RepositoriesResponse {
    @SerializedName("items") val repositoriesEntityList: List<RepositoryEntity>? = null
}