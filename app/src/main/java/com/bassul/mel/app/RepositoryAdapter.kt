package com.bassul.mel.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.databinding.LayoutRepositoryItemBinding
import kotlinx.android.synthetic.main.layout_repository_item.view.*

class RepositoryAdapter (private val context: Context, var items : List<Item>) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.ViewHolder, position: Int) {
        val item = items[position]

        holder?.name.text= item.name
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        var name = itemView.lr_tx_repository_name!!

    }

}


