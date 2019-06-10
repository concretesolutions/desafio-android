package com.abs.javarepos.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abs.javarepos.R
import com.abs.javarepos.model.Repo
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    private val items = ArrayList<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = items[position]
        holder.bind(repo)
    }

    fun addItems(items: ArrayList<Repo>) {
        this.items.addAll(items)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repo: Repo) {
            itemView.tvName.text = repo.name
            itemView.tvDescription.text = repo.description
            itemView.tvAuthorName.text = repo.authorName
        }
    }
}