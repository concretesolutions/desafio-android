package br.com.repository.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.repository.R
import br.com.repository.databinding.CardviewRepositoryBinding
import br.com.repository.model.Repository
import br.com.repository.view.holder.RepositoryHolder

class RepositoryAdapter(private val onClick: (Repository) -> Unit) : RecyclerView.Adapter<RepositoryHolder>() {

    var list: List<Repository> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val cardViewRepositoryBinding = DataBindingUtil.inflate<CardviewRepositoryBinding>(layoutInflater, R.layout.cardview_repository, parent, false)
        return RepositoryHolder(cardViewRepositoryBinding)
    }

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        val repository: Repository = list[position]
        holder.bind(repository, onClick)
    }

    override fun getItemCount() = if (list.isNotEmpty()) {
        list.size
    } else {
        0
    }

    fun setListRepository(list: List<Repository>) {
        this.list = list
        notifyDataSetChanged()
    }

}