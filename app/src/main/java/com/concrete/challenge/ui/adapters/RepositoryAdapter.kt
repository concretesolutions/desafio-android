package com.concrete.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.R
import com.concrete.challenge.presentation.model.RepositoryItem
import com.concrete.challenge.ui.viewholders.RepositoryViewHolder

class RepositoryAdapter(
    private val manager: AdapterManager
) : RecyclerView.Adapter<RepositoryViewHolder>() {

    interface AdapterManager {
        fun onRepositoryClicked(repositoryClicked: RepositoryItem)
    }

    private val repositoryList = mutableListOf<RepositoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_repository, parent, false)

        return RepositoryViewHolder(view, manager)
    }

    override fun getItemCount(): Int = repositoryList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = repositoryList[position]

        holder.bind(item)
    }

    override fun getItemId(position: Int): Long {
        return repositoryList[position].repositoryId.hashCode().toLong()
    }

    fun addItems(list: List<RepositoryItem>) {
        repositoryList.addAll(list)
        notifyDataSetChanged()
    }

}