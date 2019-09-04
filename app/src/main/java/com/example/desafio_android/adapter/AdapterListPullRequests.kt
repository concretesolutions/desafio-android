package com.example.desafio_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.model.PullRequestModel

class AdapterListPullRequests (private val itens: ArrayList<PullRequestModel>)
    : RecyclerView.Adapter<ListPullRequestsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPullRequestsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListPullRequestsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = itens.size

    override fun onBindViewHolder(holder: ListPullRequestsViewHolder, position: Int) {
        val pullRequest: PullRequestModel = itens[position]
        holder.bind(pullRequest)
    }

    fun setItens(lItens: List<PullRequestModel>){
        itens.addAll(lItens)
    }

}
