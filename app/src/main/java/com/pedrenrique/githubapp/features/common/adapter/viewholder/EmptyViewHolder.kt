package com.pedrenrique.githubapp.features.common.adapter.viewholder

import android.view.View
import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.features.common.adapter.BaseViewHolder
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory
import com.pedrenrique.githubapp.features.common.adapter.model.EmptyModelHolder
import kotlinx.android.synthetic.main.item_pull_requests_empty.view.*

class EmptyViewHolder(view: View) : BaseViewHolder<EmptyModelHolder>(view) {
    override fun bind(item: EmptyModelHolder, typesFactory: TypesFactory) {
        itemView.btnGoBack.setOnClickListener {
            typesFactory.click(Failure.Empty)
        }
    }
}