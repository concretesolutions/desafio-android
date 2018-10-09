package com.joaoibarra.gitapp.features.repository

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaoibarra.gitapp.R
import com.joaoibarra.gitapp.api.model.Pull
import com.joaoibarra.gitapp.extensions.formatDate
import com.joaoibarra.gitapp.extensions.loadCircle
import kotlinx.android.synthetic.main.item_pull.view.*

class PullAdapter(val listener: (Pull?) -> Unit) : PagedListAdapter<Pull, PullAdapter.PullViewHolder>(itemDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pull, parent, false)
        return PullViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class PullViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pull: Pull?, listener: (Pull?) -> Unit) = with(itemView) {
            tvName.text = pull?.title
            tvDescription.text = pull?.body
            tvOwnerLogin.text = pull?.user?.login
            ivAvatar.loadCircle(pull?.user?.avatarUrl)
            tvDate.formatDate(pull?.createdAt)
            setOnClickListener { listener(pull) }
        }

    }

    companion object {
        val itemDiff = object: DiffUtil.ItemCallback<Pull>() {

            override fun areItemsTheSame(old: Pull, new: Pull): Boolean {
                return old.id == new.id
            }

            override fun areContentsTheSame(old: Pull, new: Pull): Boolean {
                return old == new
            }

        }
    }
}