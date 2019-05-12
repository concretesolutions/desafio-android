package com.gdavidpb.github.ui.viewholders

import android.view.View
import com.gdavidpb.github.presentation.model.RepositoryItem
import com.gdavidpb.github.ui.adapters.PagedRepositoryAdapter
import com.gdavidpb.github.utils.onClickOnce
import kotlinx.android.synthetic.main.item_repository.view.*

open class RepositoryViewHolder(
    itemView: View,
    private val callback: PagedRepositoryAdapter.AdapterCallback
) : BaseViewHolder<RepositoryItem>(itemView) {
    override fun bindView(item: RepositoryItem) {
        with(itemView) {
            tViewRepositoryName.text = item.name
            tViewRepositoryDescription.text = item.description
            tViewRepositoryUserName.text = item.userLogin
            tViewRepositoryForkCount.text = item.forksCount
            tViewRepositoryStarCount.text = item.stargazersCount
            tViewRepositoryIssueCount.text = item.openIssuesCount

            callback.loadImage(item.userAvatarUrl, iViewRepositoryUserAvatar)

            onClickOnce { callback.onRepositoryClicked(item) }

            iViewRepositoryUserAvatar.onClickOnce { callback.onUserClicked(item) }
        }
    }
}