package com.rafaelmfer.consultinggithub.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alexzh.circleimageview.CircleImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rafaelmfer.consultinggithub.R
import com.rafaelmfer.consultinggithub.model.repositories.Item

class GitHubRepositoriesListAdapter(var repositoriesList: List<Item>) :
    RecyclerView.Adapter<GitHubRepositoriesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = repositoriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = repositoriesList[position]

        holder.apply {
            tvNameRep.text = item.name
            tvDescriptionRep.text = item.description
            tvNumberForksRep.text = item.forksCount.toString()
            tvNumbersStars.text = item.stargazersCount.toString()
            tvUserNameLogin.text = item.owner.login
            tvFullName.text = item.fullName

            Glide.with(itemView)
                .load(item.owner.avatarUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .dontAnimate()
                .into(civUser)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNameRep: TextView = itemView.findViewById(R.id.tvNameRep)
        val tvDescriptionRep: TextView = itemView.findViewById(R.id.tvDescriptionRep)
        val tvNumberForksRep: TextView = itemView.findViewById(R.id.tvNumberForksRep)
        val tvNumbersStars: TextView = itemView.findViewById(R.id.tvNumbersStars)
        val tvUserNameLogin: TextView = itemView.findViewById(R.id.tvUserNameLogin)
        val tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
        val civUser: CircleImageView = itemView.findViewById(R.id.civUser)
    }
}