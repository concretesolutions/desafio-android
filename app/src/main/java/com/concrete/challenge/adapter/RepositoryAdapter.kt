package com.concrete.challenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.R
import com.concrete.challenge.data.RepositoryEntity

class RepositoryAdapter(private val repositoryList: List<RepositoryEntity>): RecyclerView.Adapter<RepositoryViewHolder>() {

    //private val repositoryList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_repository, parent, false)

        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int = repositoryList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = repositoryList[position]
        holder.bind(item)
    }

    /*
    fun addItems(list: List<String>) {
        //repositoryList.addAll(list)
        notifyDataSetChanged()
    }
    */

}