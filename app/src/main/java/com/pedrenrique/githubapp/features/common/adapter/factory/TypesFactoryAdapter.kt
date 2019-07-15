package com.pedrenrique.githubapp.features.common.adapter.factory

import android.view.View
import com.pedrenrique.githubapp.R
import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.data.Loading
import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.features.common.adapter.BaseViewHolder
import com.pedrenrique.githubapp.features.common.adapter.viewholder.*

abstract class TypesFactoryAdapter :
    TypesFactory {

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

    override fun type(failure: Failure.Full) =
        R.layout.item_error_state

    override fun type(failure: Failure.Item) =
        R.layout.item_error

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
            R.layout.item_error ->
                ErrorItemViewHolder(view)
            R.layout.item_error_state ->
                ErrorViewHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
}