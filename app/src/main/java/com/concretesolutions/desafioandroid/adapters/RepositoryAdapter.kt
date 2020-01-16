package com.concretesolutions.desafioandroid.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.concretesolutions.desafioandroid.R
import com.concretesolutions.desafioandroid.model.Repository

class RepositoryAdapter(var repositories: List<Repository>)
            : RecyclerView.Adapter<RepositoryAdapter.RepositoryAdapterViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int)
            : RepositoryAdapterViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.repository_item, viewGroup, false)

        return RepositoryAdapterViewHolder(view)

    }

    override fun getItemCount(): Int {
        return repositories.count()
    }

    override fun onBindViewHolder(repoAdapter: RepositoryAdapterViewHolder, position: Int) {
    }

    class RepositoryAdapterViewHolder(view : View): RecyclerView.ViewHolder(view)
}