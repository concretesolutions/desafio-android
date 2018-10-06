package com.concrete.andresdavid.desafioandroid.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.concrete.andresdavid.desafioandroid.R
import com.concrete.andresdavid.desafioandroid.model.Repository
import com.concrete.andresdavid.desafioandroid.util.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repository_list_item.view.*

class RepositoryAdapter(private val repositories: List<Repository>, val context: Context)
    : RecyclerView.Adapter<RepositoryHolder>() {

    override fun getItemCount(): Int {
        return repositories.count()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        return RepositoryHolder(LayoutInflater.from(context).inflate(R.layout.repository_list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        val itemRepository = repositories[position]
        holder.bindRepository(itemRepository)
    }
}

class RepositoryHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

    private var view: View = v
    private var repository: Repository? = null

    init {
        v.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        Log.d("RecyclerView", "CLICK!")
    }

    companion object {
        private val REPOSITORY_KEY = "REPOSITORY"
    }

    fun bindRepository(repository: Repository) {
        this.repository = repository
        Picasso.get().load(repository.owner?.avatarUrl).transform(CircleTransform()).into(view.image_user)

        //TODO("validate when image does not work or does not exist")

        view.tv_repository_name.text = repository.name
        view.tv_repository_description.text = repository.description
        view.tv_user_name.text = repository.owner?.login
        view.tv_stars_count.text = repository.stargazersCount.toString()
        view.tv_fork_count.text = repository.forksCount.toString()
    }
}