package com.pedrenrique.githubapp.features.common.adapter.factory

import android.view.View
import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.data.Loading
import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.features.common.adapter.BaseViewHolder

interface TypesFactory {
    fun type(repo: Repository): Int

    fun type(pr: PullRequest): Int

    fun type(loading: Loading.Full): Int
    fun type(loading: Loading.Item): Int

    fun type(failure: Failure.Full): Int
    fun type(failure: Failure.Item): Int
    fun type(failure: Failure.Empty): Int

    fun click(repo: Repository)
    fun click(pr: PullRequest)
    fun click(failure: Failure)

    fun holder(type: Int, view: View): BaseViewHolder<*>
}