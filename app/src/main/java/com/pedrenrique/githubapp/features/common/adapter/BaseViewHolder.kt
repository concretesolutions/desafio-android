package com.pedrenrique.githubapp.features.common.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pedrenrique.githubapp.features.common.adapter.factory.TypesFactory

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T, typesFactory: TypesFactory)
}