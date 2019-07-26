package com.example.challengecskotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repo_row.view.*
import retrofit2.Call

class RepoAdapter(var repos: SearchResponse) : RecyclerView.Adapter<RepoAdapter.ViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_row, parent,false)
        return ViewHolder(view)
    }

   override fun getItemCount() = repos.items.size //quantidade de items

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repos.items[position]
        //holder.bind(repo.name, repo.description)
        holder.name.text = repo.name
        holder.description.text = repo.description
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.name
        val description: TextView = itemView.description

    }
}
