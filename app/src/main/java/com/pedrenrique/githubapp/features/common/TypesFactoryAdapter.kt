package com.pedrenrique.githubapp.features.common

import android.view.View
import com.pedrenrique.githubapp.R
import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.data.Loading
import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.platform.BaseViewHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory
import com.pedrenrique.githubapp.features.common.viewholder.GenericViewHolder
import com.pedrenrique.githubapp.features.common.viewholder.PullRequestViewHolder
import com.pedrenrique.githubapp.features.common.viewholder.RepositoryViewHolder

abstract class TypesFactoryAdapter : TypesFactory {

    override fun click(repo: Repository) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun click(pr: PullRequest) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun type(repo: Repository) =
        R.layout.item_repository

    override fun type(pr: PullRequest) =
        R.layout.item_pull_request

    override fun type(loading: Loading.Full) =
        R.layout.item_loading_full

    override fun type(loading: Loading.Item) =
        R.layout.item_loading

    override fun type(failure: Failure.Full): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun type(failure: Failure.Item): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun type(failure: Failure.Empty): Int =
        TODO("not implemented")

    override fun holder(type: Int, view: View): BaseViewHolder<*> =
        when (type) {
            R.layout.item_repository ->
                RepositoryViewHolder(view)
            R.layout.item_pull_request ->
                PullRequestViewHolder(view)
            R.layout.item_loading, R.layout.item_loading_full ->
                GenericViewHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
}