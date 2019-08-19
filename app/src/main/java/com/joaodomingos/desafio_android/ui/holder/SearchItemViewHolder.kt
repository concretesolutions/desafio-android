package com.joaodomingos.desafio_android.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaodomingos.desafio_android.R
import com.joaodomingos.desafio_android.models.SearchItensModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo.view.*

class SearchItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(searchItem: SearchItensModel?) {
        if (searchItem != null) {
            itemView.txt_name.text = searchItem.name
            itemView.txt_fullname.text = searchItem.fullName
            itemView.txt_forks .text = searchItem.forksCount.toString()
            itemView.txt_stars.text = searchItem.starsCount.toString()
            itemView.txt_username.text = searchItem.owner.userName
            itemView.txt_description.text = searchItem.description
            Picasso.get().load(searchItem.owner.avatarUrl).into(itemView.img_avatar)
        }
    }

    companion object {
        fun create(parent: ViewGroup): SearchItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repo, parent, false)
            return SearchItemViewHolder(view)
        }
    }
}