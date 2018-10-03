package com.mbstro.fmoyagonzalez.desafio_android

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.repo.view.*


class RepoAdapter(val items: ArrayList<Repo>, val listener: (Repo) -> Unit) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.repo, parent, false) as View)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name_repo: TextView = itemView.findViewById(R.id.name_repo)
        val description: TextView = itemView.findViewById(R.id.description_repo)
        val name: TextView = itemView.findViewById(R.id.name_user)
        val avatar: ImageView = itemView.findViewById(R.id.avatar_user)
        val forks: TextView = itemView.findViewById(R.id.fork_count)
        val starts: TextView = itemView.findViewById(R.id.start_count)

        fun bind(repo: Repo, listener: (Repo) -> Unit) = with(itemView) {
            name_repo.text = repo.name
            description.text = repo.description
            name.text = repo.owner.login
            Glide.with(itemView).load(repo.owner.avatar_url).into(avatar)
            forks.text = repo.forks_count.toString()
            starts.text = repo.stargazers_count.toString()
            setOnClickListener { listener(repo) }
        }
    }
}