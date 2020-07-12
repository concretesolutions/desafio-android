package com.bassul.mel.app.features.repositoriesList

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback

interface RepositoriesListContract{

    interface View{
        fun initRecyclerView()
        fun initRepositoriesCard()
        fun showCard(repositories: ArrayList<Item>)
    }

    interface Interactor{
        fun loadRepositories()
    }

    interface Repository{
        fun readRepositoryJson(callback: RepositotyAllRepositoriesCallback)
    }
}