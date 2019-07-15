package com.pedrenrique.githubapp.features.common.viewholder

import android.view.View
import com.pedrenrique.githubapp.R
import com.pedrenrique.githubapp.core.ext.setRemoteImage
import com.pedrenrique.githubapp.core.platform.BaseViewHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory
import com.pedrenrique.githubapp.features.common.model.RepositoryModelHolder
import kotlinx.android.synthetic.main.item_repository.view.*
import java.text.NumberFormat
import java.util.*

class RepositoryViewHolder(view: View) : BaseViewHolder<RepositoryModelHolder>(view) {
    override fun bind(item: RepositoryModelHolder, typesFactory: TypesFactory) {
        val repo = item.repo
        itemView.setOnClickListener {
            typesFactory.click(repo)
        }
        itemView.run {
            tvRepoName.text = repo.fullName
            tvDescription.text = repo.description.takeIf { it.isNotBlank() }
                ?: context.getText(R.string.text_item_without_description)

            val numberFormat = NumberFormat.getInstance(Locale.getDefault())
            tvFork.text = numberFormat.format(repo.forksCount)
            tvStars.text = numberFormat.format(repo.stargazersCount)

            ivOwner.setRemoteImage(repo.owner.avatarUrl) {
                error(R.drawable.ic_person)
                placeholder(R.drawable.ic_person)
            }
        }
    }
}