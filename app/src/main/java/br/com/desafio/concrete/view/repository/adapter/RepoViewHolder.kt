package br.com.desafio.concrete.view.repository.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.desafio.concrete.model.Repository
import br.com.desafio.concrete.utils.ImageLoaderUtil
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.repository_item.view.*

/**
 * Created by Malkes on 24/09/2018.
 */
class RepoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(repoItem: Repository){

        itemView?.let {
            it.tvRepoName.text = repoItem.repoName
            it.tvFullName.text = repoItem.fullName
            it.tvRepoDescription.text = repoItem.description
            it.tvUsername.text = repoItem.owner.login
            it.tvForksCount.text = repoItem.forksCount.toString()
            it.tvStarsCount.text = repoItem.starsCount.toString()
            ImageLoaderUtil.load(it.context, it.ivAccount, repoItem.owner.avatarUrl, RequestOptions.circleCropTransform())
        }

    }
}