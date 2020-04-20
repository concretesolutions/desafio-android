package com.example.githubtest.features.pullrequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtest.R
import com.example.githubtest.data.model.PullRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_pull_request.view.*
import java.util.ArrayList

class PullRequestAdapter(var pullRequests: ArrayList<PullRequest>, var pullRequestClickListener: PullRequestClickListener) :
RecyclerView.Adapter<PullRequestAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pull_request, parent, false)
        return ViewHolder(view)    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = pullRequests[position]
        holder.pullRequestTitle.text = "[${pullRequest.state.toUpperCase()}] ${pullRequest.title}"
        holder.pullRequestBody.text = pullRequest.body
        holder.pullRequestNomeSobrenome.text = pullRequest.user.login
        holder.pullRequestUserName.text = pullRequest.user.login
        Picasso.get().load(pullRequest.user.avatar_url).error(R.mipmap.ic_launcher).into(holder.pullRequestUseImage)    }


    fun addPullRequests( newPullRequests: ArrayList<PullRequest>){
        pullRequests.addAll(newPullRequests)
        pullRequests.sortByDescending { it.state}
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var pullRequestTitle: TextView = view.textViewRepository
        var pullRequestBody: TextView = view.textViewDescricao
        var pullRequestUserName: TextView = view.textViewUsername
        var pullRequestNomeSobrenome :TextView = view.textViewNomeSobrenome
        var pullRequestUseImage: ImageView = view.imageViewProfile

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            pullRequestClickListener.onClick(pullRequests[adapterPosition])
        }
    }
}