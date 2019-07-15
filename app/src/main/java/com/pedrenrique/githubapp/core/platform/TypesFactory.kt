package com.pedrenrique.githubapp.core.platform

import android.view.View
import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.data.Loading
import com.pedrenrique.githubapp.core.data.Repository

interface TypesFactory {
    fun type(repo: Repository): Int
    fun click(repo: Repository)

    fun type(loading: Loading.Full): Int
    fun type(loading: Loading.Item): Int

    fun type(failure: Failure.Full): Int
    fun type(failure: Failure.Item): Int
    fun type(failure: Failure.Empty): Int

    fun holder(type: Int, view: View): BaseViewHolder<*>
}