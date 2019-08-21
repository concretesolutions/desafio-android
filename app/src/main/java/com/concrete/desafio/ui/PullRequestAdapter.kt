package com.concrete.desafio.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.concrete.desafio.R
import com.concrete.desafio.data.PullRequest
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestViewHolder(val view: View): RecyclerView.ViewHolder(view){

    fun bindView(item: PullRequest, position: Int){
        with(view){
            titulo.text = item.titulo
            corpo.text = item.corpo
            autor.text = item.autor.login
            Glide.with(context).load(item.autor.avatar).placeholder(R.drawable.avatar).into(profile_image)
        }

        if(position % 2 == 0){
            view.setBackgroundColor(Color.parseColor("#d6d6d6"))
        }else{
            view.setBackgroundColor(Color.parseColor("#ebebeb"))
        }

    }
}

class PullRequestAdapter(val data: MutableList<PullRequest> = mutableListOf()):
    RecyclerView.Adapter<PullRequestViewHolder>() {

    var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {

        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false)

        return PullRequestViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int){

        holder.bindView(data[position], position)

        holder.view.setOnClickListener {

        }
    }


    fun add(item: PullRequest){
        data.add(item)
        notifyDataSetChanged()
    }

    fun remove(item: PullRequest){
        data.remove(item)
        notifyDataSetChanged()
    }

    fun add(itens: List<PullRequest>){
        data.clear()
        data.addAll(itens)
        notifyDataSetChanged()
    }

}