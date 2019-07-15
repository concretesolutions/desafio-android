package com.pedrenrique.githubapp.features.common.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pedrenrique.githubapp.core.platform.BaseViewHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory
import com.pedrenrique.githubapp.features.common.model.RepositoryModelHolder
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryViewHolder(view: View) : BaseViewHolder<RepositoryModelHolder>(view) {
    override fun bind(item: RepositoryModelHolder, typesFactory: TypesFactory) {
        itemView.setOnClickListener {
            typesFactory.click(item.repo)
        }
        val repo = item.repo
        itemView.run {
            tvRepoName.text = repo.fullName
            tvDescription.text = repo.description
            tvFork.text = repo.forksCount.toString()
            tvStars.text = repo.stargazersCount.toString()
            Glide.with(ivOwner)
                    .load(repo.owner.avatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivOwner)
        }
    }
}