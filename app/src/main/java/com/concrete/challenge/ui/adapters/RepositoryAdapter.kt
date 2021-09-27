package com.concrete.challenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concrete.challenge.R
import com.concrete.challenge.data.RepositoryEntity
import com.concrete.challenge.ui.viewholders.RepositoryViewHolder

class RepositoryAdapter(
    private val repositoryList: List<RepositoryEntity>,
    private val manager: AdapterManager
) : RecyclerView.Adapter<RepositoryViewHolder>() {

    //private val repositoryList = mutableListOf<String>()

    interface AdapterManager {
        fun onRepositoryClicked(repositoryClicked: RepositoryEntity)
    }

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

    /*
    fun addItems(list: List<String>) {
        //repositoryList.addAll(list)
        notifyDataSetChanged()
    }
    */

}