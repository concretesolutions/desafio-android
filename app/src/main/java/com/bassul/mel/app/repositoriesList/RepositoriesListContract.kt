package com.bassul.mel.app.repositoriesList

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.callback.RepositotySelectedRepositoriesCallback

interface RepositoriesListContract{

    interface View{
        fun initRecyclerView()
        fun initRepositoriesCard()
        fun showCard(repositories: ArrayList<Item>)
    }

    interface Presenter{
        fun showCard(ItemModel: ArrayList<Item>)

    }

    interface Interactor{
        fun loadRepositories(pages : Int)
        fun getSelectedItem(item: Item)
    }

    interface Repository{
        fun readRepositoryJson(pages : Int, callback: RepositotyAllRepositoriesCallback)
        fun readPullRequestJson(login : String, nameRepository : String, callback: RepositotySelectedRepositoriesCallback)
    }
}