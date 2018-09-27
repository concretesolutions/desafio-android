package br.com.desafio.concrete.view.pullrequest.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.desafio.concrete.R
import br.com.desafio.concrete.model.PullRequest
import br.com.desafio.concrete.view.pullrequest.PullRequestContract

/**
 * Created by Malkes on 24/09/2018.
 */
class PullRequestAdapter(private var items: List<PullRequest>, private val view: PullRequestContract.View): RecyclerView.Adapter<PullRequestViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pullrequest_item, parent, false)
        return PullRequestViewHolder(view)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int){
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            view.onListItemClicked(item)
        }
    }

}