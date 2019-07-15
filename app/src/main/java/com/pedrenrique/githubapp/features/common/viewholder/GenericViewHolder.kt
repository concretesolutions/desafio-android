package com.pedrenrique.githubapp.features.common.viewholder

import android.view.View
import com.pedrenrique.githubapp.core.platform.BaseViewHolder
import com.pedrenrique.githubapp.core.platform.ModelHolder
import com.pedrenrique.githubapp.core.platform.TypesFactory

class GenericViewHolder(view: View) : BaseViewHolder<ModelHolder>(view) {
    override fun bind(item: ModelHolder, typesFactory: TypesFactory) {
        // nothing
    }
}