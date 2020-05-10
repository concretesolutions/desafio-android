package com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hotmail.fignunes.desafioconcretesolution.R
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class GitRepositoryAdapter (
    private val context: Activity,
    private val gitRepositoryItems: List<GitRepositoryItem>,
    private val clickGitRepositoryItem: ClickGitRepositoryItem,
    private val setPosition: SetPosition
) :
    RecyclerView.Adapter<GitRepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        val holder = ViewHolder(view)
        view.setTag(holder)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gitRepositoryItem = gitRepositoryItems[position]
        holder.name.text = gitRepositoryItem.name
        holder.description.text = gitRepositoryItem.description
        holder.userLogin.text = gitRepositoryItem.owner.login
        holder.star.text = gitRepositoryItem.stargazers_count.toString()
        holder.forks.text = gitRepositoryItem.forks_count.toString()

        holder.progressBar.setVisibility(View.GONE)
        loadImage(gitRepositoryItem, holder)

        holder.layout.setOnClickListener { clickGitRepositoryItem.click(gitRepositoryItem) }
        setPosition.set(position)
    }

    private fun loadImage(item: GitRepositoryItem, holder: ViewHolder) {
        holder.progressBar.setVisibility(View.VISIBLE)
        Picasso.get().load(item.owner.avatar_url)
            .error(R.drawable.ic_avatar)
            .into(holder.image, object : Callback {
                override fun onSuccess() {
                    holder.progressBar.setVisibility(View.GONE)
                }

                override fun onError(e: Exception?) {
                    holder.progressBar.setVisibility(View.GONE)
                }
            })
    }

    override fun getItemCount(): Int {
        return gitRepositoryItems.size
    }

    interface ClickGitRepositoryItem {
        fun click(gitRepositoryItem: GitRepositoryItem)
    }

    interface SetPosition {
        fun set(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var name: TextView
        internal var description: TextView
        internal var forks: TextView
        internal var star: TextView
        internal var userLogin : TextView
        internal var userSurname : TextView
        internal var progressBar: ProgressBar
        internal var image: ImageView
        internal var layout: LinearLayout

        init {
            layout = itemView.findViewById(R.id.itemGitRepositoryLinearLayout) as LinearLayout
            name = itemView.findViewById(R.id.itemGitRepositoryName) as TextView
            description = itemView.findViewById(R.id.itemGitRepositoryDescription) as TextView
            forks = itemView.findViewById(R.id.itemGitRepositoryBranch) as TextView
            star = itemView.findViewById(R.id.itemGitRepositoryStar) as TextView
            userLogin = itemView.findViewById(R.id.itemGitRepositoryUserLogin) as TextView
            userSurname = itemView.findViewById(R.id.itemGitRepositoryUserSurname) as TextView
            image = itemView.findViewById(R.id.itemGitRepositoryImage) as ImageView
            progressBar = itemView.findViewById(R.id.itemGitRepositoryProgressBar) as ProgressBar
        }
    }
}