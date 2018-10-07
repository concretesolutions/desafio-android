package com.joaoibarra.gitapp.features.repository

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaoibarra.gitapp.R
import com.joaoibarra.gitapp.api.model.Item

import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter : PagedListAdapter<Item, RepositoryAdapter.ItemViewHolder>(itemDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val repository = getItem(position)
        holder.tvName.text = repository?.name
        holder.tvDescription.text = repository?.description
        holder.tvForkCount.text = repository?.forksCount
        holder.tvStarGazerCount.text = repository?.stargazersCount
        holder.tvOwnerLogin.text = repository?.owner?.login
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvName
        val tvDescription = itemView.tvDescription
        val tvForkCount = itemView.tvForkCount
        val tvStarGazerCount = itemView.tvStarGazerCount
        val tvOwnerLogin = itemView.tvOwnerLogin
    }

    companion object {
        val itemDiff = object: DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(old: Item, new: Item): Boolean {
                return old.id == new.id

            }

            override fun areContentsTheSame(old: Item, new: Item): Boolean {
                return old == new
            }

        }
    }
}