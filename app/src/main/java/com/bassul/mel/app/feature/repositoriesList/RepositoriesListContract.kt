package com.bassul.mel.app.feature.repositoriesList

import androidx.annotation.StringRes
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.feature.repositoriesList.repository.model.RepositoriesListResponse

interface RepositoriesListContract{

    interface View{
        fun initRecyclerView()
        fun initRepositoriesCard()
        fun showCard(repositories: ArrayList<Item>)

        fun showErrorCard(@StringRes errorMessage: Int)

    }

    interface Presenter{
        fun showCard(ItemModel: ArrayList<Item>)
        fun errorShowCard(@StringRes errorMessage: Int)

    }

    interface Interactor{
        fun loadRepositories(pages : Int)
        fun convertGithubRepositoriesListResponseToRepositoriesList(listResponse: RepositoriesListResponse): ArrayList<Item>
    }

    interface Repository{
        fun readRepositoryJson(pages : Int, callback: RepositotyAllRepositoriesCallback)

    }
}