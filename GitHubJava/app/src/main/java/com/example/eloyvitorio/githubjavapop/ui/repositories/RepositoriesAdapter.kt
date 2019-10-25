package com.example.eloyvitorio.githubjavapop.ui.repositories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.eloyvitorio.githubjavapop.R
import com.example.eloyvitorio.githubjavapop.data.model.Repository

class RepositoriesAdapter(private val clickItem: (String, String) -> Unit)
    : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {
    private var listOfRepositories = mutableListOf<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfRepositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = listOfRepositories.get(position)
        holder.bindView(repository, clickItem)
    }

    fun addAll(newListRepositories: List<Repository>) {
        if (listOfRepositories.isNullOrEmpty()) {
            listOfRepositories.addAll(newListRepositories)
            notifyDataSetChanged()
        } else {
            val curSize = itemCount
            listOfRepositories.addAll(newListRepositories)
            notifyItemRangeInserted(curSize, listOfRepositories.size - 1)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(repository: Repository, onClick: (String, String) -> Unit) {
            val repositoryTitle = itemView.findViewById<TextView>(R.id.textViewMainRepositoryTitle)
            val repositoryDescription = itemView.findViewById<TextView>(R.id.textViewMainRepositoryDescription)
            val repositoryForkNumber = itemView.findViewById<TextView>(R.id.textViewMainForkNumber)
            val repositoryStarNumber = itemView.findViewById<TextView>(R.id.textViewMainStartNumber)
            val repositoryUserPhoto = itemView.findViewById<ImageView>(R.id.imageViewMainUserPhoto)
            val repositoryUserName = itemView.findViewById<TextView>(R.id.textViewMainUserName)
            val repositoryTrueName = itemView.findViewById<TextView>(R.id.textViewMainName)

            repositoryTitle.text = repository.name
            repositoryDescription.text = repository.description
            repositoryForkNumber.text = repository.forks.toString()
            repositoryStarNumber.text = repository.stargazersCount.toString()
            repositoryUserName.text = repository.owner.login
            repositoryTrueName.text = repository.fullName
            Glide.with(itemView.context)
                    .load(repository.owner.avatarUrl)
                    .error(R.drawable.ic_unavailable)
                    .into(repositoryUserPhoto)

            val title = repositoryTitle.text.toString()
            val userName = repositoryUserName.text.toString()

            itemView.setOnClickListener {
                onClick(userName, title)
            }
        }
    }
}