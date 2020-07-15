package com.bassul.mel.app.feature.repositoriesList

import androidx.annotation.StringRes
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse

interface RepoListContract {

    interface View {
        fun showCard(repositories: ArrayList<Item>)
        fun showErrorRepoList(@StringRes errorMessage: Int)
        fun initRepositoriesCard(pages: Int)
    }

    interface Presenter {
        fun showCard(ItemModel: ArrayList<Item>)
        fun errorShowCard(@StringRes errorMessage: Int)

    }

    interface Interactor {
        fun loadRepositories(pages: Int)
    }

    interface Repository {
        fun readRepositoryJson(pages: Int, callback: RepositotyAllRepositoriesCallback)

    }
}