package com.bassul.mel.app.feature.repositoriesList.presenter

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.feature.repositoriesList.RepoListContract
import kotlin.collections.ArrayList

class RepoPresenterImpl (val view: RepoListContract.View) :
    RepoListContract.Presenter {

    override fun showCard(ItemModel: ArrayList<Item>) {
        //view.hideLoading()
        view.showCard(ItemModel)
    }

    override fun errorShowCard(errorMessage: Int) {
        view.showErrorCard(errorMessage)
    }

}