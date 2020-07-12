package com.bassul.mel.app.callback

import com.bassul.mel.app.repositoriesList.repository.model.RepositoriesListResponse

interface RepositotyAllRepositoriesCallback {
    fun onSuccess(repositoriesList : RepositoriesListResponse)
}