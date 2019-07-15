package com.pedrenrique.githubapp.features.common.viewholder

import android.view.View
import com.pedrenrique.githubapp.core.platform.BaseViewHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory
import com.pedrenrique.githubapp.features.common.model.RepositoryModelHolder

class ErrorViewHolder(view: View) : BaseViewHolder<RepositoryModelHolder>(view) {
    override fun bind(item: RepositoryModelHolder, typesFactory: TypesFactory) {
        itemView.setOnClickListener {
            typesFactory.click(item.repo)
        }
    }
}