package com.pedrenrique.githubapp.features.common.adapter.viewholder

import android.text.format.DateFormat
import android.view.View
import com.pedrenrique.githubapp.R
import com.pedrenrique.githubapp.core.ext.setRemoteImage
import com.pedrenrique.githubapp.features.common.adapter.BaseViewHolder
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory
import com.pedrenrique.githubapp.features.common.adapter.model.PullRequestModelHolder
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestViewHolder(view: View) : BaseViewHolder<PullRequestModelHolder>(view) {
    override fun bind(item: PullRequestModelHolder, typesFactory: TypesFactory) {
        val pullRequest = item.pr
        itemView.setOnClickListener {
            typesFactory.click(pullRequest)
        }
        itemView.run {
            tvTitle.text = pullRequest.title
            tvBody.text = pullRequest.body.takeIf { it.isNotBlank() }?.replace("\n", "")
                ?: context.getText(R.string.text_item_without_description)
            tvInfo.text = context.getString(
                R.string.text_pull_request_info,
                pullRequest.number,
                DateFormat.getDateFormat(context).format(pullRequest.createdAt),
                pullRequest.user.login
            )

            ivUser.setRemoteImage(pullRequest.user.avatarUrl) {
                error(R.drawable.ic_person)
                placeholder(R.drawable.ic_person)
            }
        }
    }
}