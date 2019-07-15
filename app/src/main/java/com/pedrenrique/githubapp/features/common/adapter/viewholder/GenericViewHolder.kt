package com.pedrenrique.githubapp.features.common.adapter.viewholder

import android.view.View
import com.pedrenrique.githubapp.features.common.adapter.BaseViewHolder
import com.pedrenrique.githubapp.features.common.adapter.ModelHolder
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory

class GenericViewHolder(view: View) : BaseViewHolder<ModelHolder>(view) {
    override fun bind(item: ModelHolder, typesFactory: TypesFactory) {
        // nothing
    }
}