package com.example.githubtest.features.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.githubtest.R
import com.example.githubtest.data.model.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repository.*
import kotlinx.android.synthetic.main.activity_repository.view.*
import kotlinx.android.synthetic.main.item_view_repository.view.*


class RepositoryAdapter (var repositories: ArrayList<Repository>, var repositoryClickListener: RepositoryClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoading = false

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_repository, parent, false)
        return RepositoruViewHolder(view, repositoryClickListener)
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoruViewHolder) holder.bindViewHolder(repositories[position])

    }

    fun addRepositories(newRepositories: ArrayList<Repository>) {
        repositories.addAll(newRepositories)
        this.notifyDataSetChanged()
    }

    fun startLoading(view: View) {
        isLoading = true
        view.progressBar2.visibility = View.VISIBLE
        notifyDataSetChanged()
    }

    fun stopLoading(view: View) {
        isLoading = false
        view.progressBar2.visibility = View.GONE
        notifyDataSetChanged()
    }

    fun isLoading(): Boolean {
        return isLoading
    }

    inner class RepositoruViewHolder(
        private val view: View,
        private val repositoryClickListener: RepositoryClickListener
    ) : RecyclerView.ViewHolder(view) {

        var repositoryUserImage: ImageView = view.imageViewProfile
        var repositoryName: TextView = view.textViewRepository
        var repositoryUserName: TextView = view.textViewUsername
        var repositoryFullName: TextView = view.textViewNomeSobrenome
        var repositoryDescription: TextView = view.textViewDescricao
        var repositoryStar: TextView = view.textViewStars
        var repositoryFork: TextView = view.textViewForks

        fun bindViewHolder(repository: Repository) {
            view.setOnClickListener { repositoryClickListener.onClick(repository) }
            repositoryName.text = repository.name
            repositoryDescription.text = repository.description
            repositoryStar.text = repository.stargazers_count.toString()
            repositoryFork.text = repository.forks_count.toString()
            repositoryUserName.text = repository.owner.login
            repositoryFullName.text = repository.full_name
            Picasso.get().load(repository.owner.avatar_url)
                .error(R.mipmap.ic_launcher)
                .into(repositoryUserImage)

        }
    }
}