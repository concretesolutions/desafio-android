package com.example.desafio_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.model.RepositorieModel

class AdapterListRepositories(private val itens: ArrayList<RepositorieModel>)
    : RecyclerView.Adapter<ListRepositorieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRepositorieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListRepositorieViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = itens.size

    override fun onBindViewHolder(holder: ListRepositorieViewHolder, position: Int) {
        val repositorie: RepositorieModel = itens[position]
        holder.bind(repositorie)
    }

    fun setItens(lItens: List<RepositorieModel>){
        itens.addAll(lItens)
    }

    fun getItem(position: Int): RepositorieModel {
        return itens[position]
    }
}
