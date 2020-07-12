package com.bassul.mel.app.repositoriesList

import com.bassul.mel.app.domain.Item
import java.util.ArrayList

class RepoPresenterImpl (val view: RepositoriesListContract.View) : RepositoriesListContract.Presenter {

    override fun showCard(ItemModel: ArrayList<Item>) {
        return view.showCard(ItemModel)
    }
}