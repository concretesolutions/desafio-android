package com.pedrenrique.githubapp.features.common.adapter.viewholder

import android.view.View
import com.pedrenrique.githubapp.features.common.adapter.BaseViewHolder
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory
import com.pedrenrique.githubapp.features.common.adapter.model.ErrorItemModelHolder
import kotlinx.android.synthetic.main.item_error.view.*

class ErrorItemViewHolder(view: View) : BaseViewHolder<ErrorItemModelHolder>(view) {
    override fun bind(item: ErrorItemModelHolder, typesFactory: TypesFactory) {
        itemView.btnLoadMore.setOnClickListener {
            typesFactory.click(item.error)
        }
    }
}