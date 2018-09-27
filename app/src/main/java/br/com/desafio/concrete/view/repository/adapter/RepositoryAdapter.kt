package br.com.desafio.concrete.view.repository.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.desafio.concrete.R
import br.com.desafio.concrete.model.Repository
import br.com.desafio.concrete.view.repository.RepositoryListContract

/**
 * Created by Malkes on 24/09/2018.
 */
class RepositoryAdapter(private var items: ArrayList<Repository>, private val view: RepositoryListContract.View): RecyclerView.Adapter<RepoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.repository_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: RepoViewHolder, position: Int){
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            view.onListItemClicked(item)
        }
    }

    fun addMoreItems(moreItems: List<Repository>){
        items.addAll(moreItems)
        notifyDataSetChanged()
    }

    fun getDataSource(): ArrayList<Repository>{
        return items
    }
}