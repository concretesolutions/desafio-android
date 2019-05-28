package com.jmc.desafioandroidkotlin.presentation.ui.viewholders

import android.view.View
import com.jmc.desafioandroidkotlin.R
import com.jmc.desafioandroidkotlin.presentation.model.PullItem
import com.jmc.desafioandroidkotlin.presentation.ui.adapters.PullAdapter
import com.jmc.desafioandroidkotlin.utils.onClickOnce

import kotlinx.android.synthetic.main.item_pull.view.*

open class PullViewHolder(
    itemView: View,
    private val callback: PullAdapter.AdapterCallback
) : BaseViewHolder<PullItem>(itemView) {
    override fun bindView(item: PullItem) {
        with(itemView) {
            tViewPullName.text = item.title
            tViewPullDescription.text = item.body
            tViewPullUserName.text = item.userLogin

            tViewPullInfo.text = context.getString(R.string.label_info_pulls, item.number, item.message)

            callback.loadImage(item.userAvatarUrl, iViewPullUserAvatar)

            onClickOnce { callback.onPullClicked(item) }

            iViewPullUserAvatar.onClickOnce { callback.onUserClicked(item) }
        }
    }
}