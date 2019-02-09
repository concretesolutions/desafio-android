package com.accenture.desafioandroid.presentation.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.accenture.desafioandroid.R
import com.accenture.desafioandroid.mvvm.model.PullRequest
import com.accenture.desafioandroid.utils.ellipsis
import com.accenture.desafioandroid.utils.setDate
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_pull_request.view.*


class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val context = itemView.context
    private val name = itemView.tv_name
    private val description = itemView.tv_description
    val content = itemView.cv_content!!
    private val profileUser = itemView.profile_image
    private val nickName = itemView.tv_nick_name
    private val userName = itemView.tv_user_name
    private val date = itemView.tv_date


    fun setDataPull(items: PullRequest) {
        name.text = items.title

        description.text = ellipsis(items.body!!, 100)
        date.text = setDate(items.updateUp!!)

        val owner = items.user
        nickName.text = "@" + owner!!.login
        userName.text = owner.login
        Glide.with(context)
            .load(owner.avatarUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_btc)
            )
            .into(profileUser)
    }

}