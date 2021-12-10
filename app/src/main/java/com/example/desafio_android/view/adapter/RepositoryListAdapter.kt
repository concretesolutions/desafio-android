package com.example.desafio_android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafio_android.R
import com.example.desafio_android.data.model.Item
import com.example.desafio_android.view.RepositoryCallback
import com.example.desafio_android.view.adapter.diffutil.RepositoryDiffUtil
import kotlinx.android.synthetic.main.concrete_repository_item.view.*

class RepositoryListAdapter (val callback : RepositoryCallback) :
    androidx.recyclerview.widget.ListAdapter <Item,
            RepositoryListAdapter.RepositoryViewHolder>(RepositoryDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.concrete_repository_item,
                parent,
                false)

        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class RepositoryViewHolder(
        view : View
    ) : RecyclerView.ViewHolder(view){

        private val imageUser = view.ivChild
        private val userName = view.tvItemUsername
        private val repositoryName = view.tvItemName
        private val repositoryDesc = view.tvItemDescription
        private val repositoryFork = view.tvfork
        private val repositoryStar = view.tvstars

        fun bind(item : Item){

            repositoryName.text = item.name
            repositoryDesc.text = item.description
            repositoryFork.text = item.forks_count.toString()
            repositoryStar.text = item.stargazers_count.toString()
            imageUser.load(item.owner.avatar_url){
                crossfade(true)
                placeholder(R.drawable.ic_person)
            }
            userName.text = item.owner.login

            repositoryDesc.setOnClickListener {
                callback.onRepoClicked(item.owner.login, item.name)
            }
        }

    }

}