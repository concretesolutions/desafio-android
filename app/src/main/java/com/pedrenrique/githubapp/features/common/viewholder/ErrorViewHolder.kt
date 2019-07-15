package com.pedrenrique.githubapp.features.common.viewholder

import android.view.View
import com.pedrenrique.githubapp.core.platform.BaseViewHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory
import com.pedrenrique.githubapp.features.common.model.ErrorItemModelHolder
import com.pedrenrique.githubapp.features.common.model.ErrorModelHolder
import com.pedrenrique.githubapp.features.common.model.RepositoryModelHolder

class ErrorViewHolder(view: View) : BaseViewHolder<ErrorModelHolder>(view) {
    override fun bind(item: ErrorModelHolder, typesFactory: TypesFactory) {
        itemView.setOnClickListener {
            typesFactory.click(item.error)
        }
    }
}