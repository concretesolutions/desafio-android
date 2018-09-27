package br.com.desafio.concrete.view.pullrequest.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.desafio.concrete.model.PullRequest
import br.com.desafio.concrete.utils.ImageLoaderUtil
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.pullrequest_item.view.*


/**
 * Created by Malkes on 24/09/2018.
 */
class PullRequestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(pullRequest: PullRequest){

        itemView?.let {
            it.tvTitle.text = pullRequest.title
            it.tvBody.text = pullRequest.body
            it.tvUsername.text = pullRequest.user.login
            ImageLoaderUtil.load(it.context, it.ivAccount,pullRequest.user.avatarUrl, RequestOptions.circleCropTransform())
        }

    }
}