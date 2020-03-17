package com.rafaelmfer.consultinggithub.mvvm.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rafaelmfer.consultinggithub.R
import com.rafaelmfer.consultinggithub.mvvm.model.repositories.Item
import de.hdodenhof.circleimageview.CircleImageView

class GitHubRepositoriesListAdapter(var repositoriesList: List<Item>, private val listener: OnClickListenerGitHub) :
    RecyclerView.Adapter<GitHubRepositoriesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.repository_item, parent, false)
    )

    override fun getItemCount(): Int = repositoriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        repositoriesList[position].run {
            holder.apply {
                tvNameRep.text = name
                tvDescriptionRep.text = description
                tvNumberForksRep.text = forksCount.toString()
                tvNumbersStars.text = stargazersCount.toString()
                tvUserNameLogin.text = owner.login
                tvFullName.text = fullName

                Glide.with(itemView)
                    .load(owner.avatarUrl)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .dontAnimate()
                    .into(circleIvUser)

                itemView.setOnClickListener {
                    listener.onClickOpenPullRequestsList(owner.login, name)
                }
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNameRep: TextView = itemView.findViewById(R.id.tvNameRep)
        val tvDescriptionRep: TextView = itemView.findViewById(R.id.tvDescriptionRep)
        val tvNumberForksRep: TextView = itemView.findViewById(R.id.tvNumberForksRep)
        val tvNumbersStars: TextView = itemView.findViewById(R.id.tvNumbersStars)
        val tvUserNameLogin: TextView = itemView.findViewById(R.id.tvUserNameLogin)
        val tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
        val circleIvUser: CircleImageView = itemView.findViewById(R.id.civUser)
    }
}