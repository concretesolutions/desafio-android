package com.bassul.mel.app.feature.repositoriesList

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.PullRequest
import kotlin.collections.ArrayList

class RepoPresenterImpl (val view: RepositoriesListContract.View) : RepositoriesListContract.Presenter {

    override fun showCard(ItemModel: ArrayList<Item>) {
        return view.showCard(ItemModel)
    }


    override fun errorShowCard(errorMessage: Int) {
        return view.showErrorCard(errorMessage)
    }

}