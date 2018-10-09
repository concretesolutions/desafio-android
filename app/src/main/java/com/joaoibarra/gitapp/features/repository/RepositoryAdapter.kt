package com.joaoibarra.gitapp.features.repository

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaoibarra.gitapp.R
import com.joaoibarra.gitapp.api.model.Repo
import com.joaoibarra.gitapp.extensions.loadCircle

import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(val listener: (Repo?) -> Unit) : PagedListAdapter<Repo, RepositoryAdapter.ItemViewHolder>(itemDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repository: Repo?, listener: (Repo?) -> Unit) = with(itemView) {
            tvName.text = repository?.name
            tvDescription.text = repository?.description
            tvForkCount.text = repository?.forksCount
            tvStarGazerCount.text = repository?.stargazersCount
            tvOwnerLogin.text = repository?.owner?.login
            ivAvatar.loadCircle(repository?.owner?.avatarUrl)
            setOnClickListener { listener(repository) }
        }
    }

    companion object {
        val itemDiff = object: DiffUtil.ItemCallback<Repo>() {

            override fun areItemsTheSame(old: Repo, new: Repo): Boolean {
                return old.id == new.id
            }

            override fun areContentsTheSame(old: Repo, new: Repo): Boolean {
                return old == new
            }

        }
    }

}
