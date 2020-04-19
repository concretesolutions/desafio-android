package com.example.githubtest.features.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtest.R
import com.example.githubtest.data.model.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_repository.view.*

class RepositoryAdapter (var repositories: ArrayList<Repository>, var repositoryClickListener: RepositoryClickListener)
    : RecyclerView.Adapter<RepositoryAdapter.RepositoruViewHolder>(){

    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoruViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_repository, parent, false)
        return RepositoruViewHolder(view)
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RepositoruViewHolder, position: Int) {
        val repository  = repositories.get(position)
        holder.bindViewHolder(repository)
    }
    fun addRepositories(newRepositories: ArrayList<Repository>) {
        repositories.addAll(newRepositories)
        this.notifyDataSetChanged()
    }

    fun startLoading() {
        isLoading = true
        notifyDataSetChanged()
    }

    fun stopLoading() {
        isLoading = false
        notifyDataSetChanged()
    }

    fun isLoading(): Boolean {
        return isLoading
    }

    inner class RepositoruViewHolder(private val view: View)
        : RecyclerView.ViewHolder(view){

        var mRepositoryUserImage: ImageView = view.imageViewProfile
        var mRepositoryName: TextView = view.textViewRepository
        var mRepositoryUserName: TextView = view.textViewUsername
        var mRepositoryFullName: TextView = view.textViewNomeSobrenome
        var mRepositoryDescription: TextView = view.textViewDescricao
        var mRepositoryStar: TextView = view.textViewStars
        var mRepositoryFork: TextView = view.textViewForks

        fun bindViewHolder(repository: Repository){
            view.setOnClickListener { repositoryClickListener.onClick(repository) }
            mRepositoryName.text = repository.name
            mRepositoryDescription.text = repository.description
            mRepositoryStar.text = repository.stargazers_count.toString()
            mRepositoryFork.text = repository.forks_count.toString()
            mRepositoryUserName.text = repository.owner.login
            mRepositoryFullName.text = ""
            Picasso.get().load(repository.owner.avatar_url)
                .error(R.mipmap.ic_launcher)
                .into(mRepositoryUserImage)

        }

    }
}