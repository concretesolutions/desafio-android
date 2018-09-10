package com.concrete.desafioandroid.features.repos

import com.concrete.desafioandroid.data.models.Repo
import com.concrete.desafioandroid.features.base.BaseView

interface ReposView: BaseView {
    fun onGetReposListSuccess(reposList: List<Repo>)
    fun onFetchDetails(position: Int)
    fun loading(loading: Boolean)
}