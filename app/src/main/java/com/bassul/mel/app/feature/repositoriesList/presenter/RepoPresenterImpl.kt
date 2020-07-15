package com.bassul.mel.app.feature.repositoriesList.presenter

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.feature.repositoriesList.RepoListContract

class RepoPresenterImpl(val view: RepoListContract.View) :
    RepoListContract.Presenter {

    override fun showCard(ItemModel: ArrayList<Item>) {
        view.showCard(ItemModel)
    }

    override fun errorShowCard(errorMessage: Int) {
        view.showErrorRepoList(errorMessage)
    }

}