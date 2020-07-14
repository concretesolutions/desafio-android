package com.bassul.mel.app.callback

import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse

interface RepositotyAllRepositoriesCallback {
    fun onSuccess(repositoriesList : RepositoriesListResponse)
    fun onError(errorMessage : String)
}