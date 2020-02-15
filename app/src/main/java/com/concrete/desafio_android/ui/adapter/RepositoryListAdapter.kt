package com.concrete.desafio_android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.concrete.desafio_android.R
import com.concrete.desafio_android.domain.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_repository.view.*

class RepositoryListAdapter (
    private val repositories: ArrayList<Repository>,
    private val context: Context,
    private val listener: (Repository) -> Unit
): RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repository: Repository, listener: (Repository) -> Unit) = with(itemView) {
            itemView.textview_repository_description.text = repository.description
            itemView.textview_repository_name.text = repository.name
            itemView.textview_repository_fork_counter.text = repository.forks_count.toString()
            itemView.textview_repository_star_counter.text = repository.stargazers_count.toString()
            itemView.textview_repository_owner_username.text = repository.owner.login
            Picasso.get()
                .load(repository.owner.avatar_url)
                .resize(48, 48)
                .centerCrop()
//                .placeholder() TODO
//                .error()      TODO
                .into(itemView.repository_owner_avatar)
            setOnClickListener { listener(repository) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_repository, parent, false))
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositories[position], listener)
    }
}