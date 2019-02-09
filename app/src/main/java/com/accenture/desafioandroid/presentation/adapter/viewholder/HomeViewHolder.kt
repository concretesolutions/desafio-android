package com.accenture.desafioandroid.presentation.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.accenture.desafioandroid.R
import com.accenture.desafioandroid.mvvm.model.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_repository.view.*


class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val context = itemView.context
        private val name = itemView.tv_name
        private val description = itemView.tv_description
        val content = itemView.cv_content!!
        private val fork = itemView.tv_fork
        private val star = itemView.tv_star
        private val profileUser = itemView.profile_image
        private val nickName = itemView.tv_nick_name
        private val userName = itemView.tv_user_name

    fun setDataWallet(items: Item) {
        name.text = items.name
            description.text = items.description
            fork.text = items.forksCount.toString()
            star.text = items.stargazersCount.toString()

            val owner = items.owner
            userName.text = owner!!.login
            nickName.text = "@"+owner.login

            Glide.with(context)
                .load(owner.avatarUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_btc)
                )
                .into(profileUser)

    }
}