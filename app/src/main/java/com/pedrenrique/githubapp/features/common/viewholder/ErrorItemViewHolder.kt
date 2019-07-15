package com.pedrenrique.githubapp.features.common.viewholder

import android.view.View
import com.pedrenrique.githubapp.core.platform.BaseViewHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory
import com.pedrenrique.githubapp.features.common.model.ErrorItemModelHolder
import com.pedrenrique.githubapp.features.common.model.RepositoryModelHolder

class ErrorItemViewHolder(view: View) : BaseViewHolder<ErrorItemModelHolder>(view) {
    override fun bind(item: ErrorItemModelHolder, typesFactory: TypesFactory) {
        itemView.setOnClickListener {
            typesFactory.click(item.error)
        }
    }
}