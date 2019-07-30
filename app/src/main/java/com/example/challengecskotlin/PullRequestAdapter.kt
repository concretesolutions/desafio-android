package com.example.challengecskotlin

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pr_row.view.*


class PullRequestAdapter (var pullRequests: List<PullRequestObject>): RecyclerView.Adapter<PullRequestAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pr_row, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pullRequests.size  //quantidade de items

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pr = pullRequests[position]
        holder.name.text = pr.title
        holder.description.text = pr.body

        holder.itemView.setOnClickListener {
            d("onClick", "clicado: $pr")
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.pr_name
        val description: TextView = itemView.pr_description
    }
}
