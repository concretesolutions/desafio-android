package com.concretesolutions.desafioandroid.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.model.Repository
import kotlinx.android.synthetic.main.repository_counts.view.*
import kotlinx.android.synthetic.main.title_description.view.*
import kotlinx.android.synthetic.main.user_avatar.view.*
import com.squareup.picasso.Picasso



class RepositoryAdapter(private val repositories: List<Repository>, private val listener: OnItemClickListener)
            : RecyclerView.Adapter<RepositoryAdapter.RepositoryAdapterViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int)
            : RepositoryAdapterViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.repository_item, viewGroup, false)

        return RepositoryAdapterViewHolder(view)

    }

    override fun getItemCount() = repositories.count()

    override fun onBindViewHolder(holder: RepositoryAdapterViewHolder, position: Int) {
        holder.bind(repositories[position], listener)
    }

    class RepositoryAdapterViewHolder(private val view : View): RecyclerView.ViewHolder(view) {
        fun bind(repository: Repository, listener: OnItemClickListener) {
            with(view) {
                ctForks.text = repository.forksCount.toString()
                ctStars.text = repository.starsCount.toString()
                title.text = repository.name
                description.text = repository.description
                fullName.text = repository.owner.login
                firstName.text = repository.owner.login

                setOnClickListener(View.OnClickListener { listener.onItemClick(repository)  })
            }
            Picasso.with(view.context).load(repository.owner.avatarUrl)
                .into(view.fotoUser)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(repository: Repository)
    }
}