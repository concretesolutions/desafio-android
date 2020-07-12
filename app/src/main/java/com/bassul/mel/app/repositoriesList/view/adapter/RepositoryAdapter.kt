package com.bassul.mel.app.repositoriesList.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.R
import kotlinx.android.synthetic.main.layout_repository_item.view.*

class RepositoryAdapter (private val context: Context, var items : List<Item>) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder?.name.text = item.name
        holder?.description.text = item.description
        holder?.stars.text= item.stargazers_count
        holder?.forks.text= item.forks_count
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        var name = itemView.lr_tx_repository_name!!
        var description = itemView.lr_tx_repository_description!!
        var avatarOwner = itemView.lr_im_avatar
        var nameOwner = itemView.lr_tx_login
        var stars = itemView.lr_tx_starts
        var forks = itemView.lr_tx_forks

    }

}


