package com.carol.apirest.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.carol.apirest.Model.Item
import com.carol.apirest.R
import kotlinx.android.synthetic.main.listaview_principal.view.*

class ItemAdapter (internal var context: Context, internal var postList: List<Item>) : RecyclerView.Adapter<ItemViewHold> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHold {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.listaview_principal,parent,false)

        return ItemViewHold(itemView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ItemViewHold, position: Int) {

        holder.txvNomeUsuario.text = postList[position].nomeUsuario
        holder.txvQtdFork.text = postList[position].qtdFork.toString()
        holder.txvQtdStar.text = postList[position].qtdStar.toString()
        holder.txvRepositorio.text = postList[position].nomeRepositorio
        //holder.imvAutor.setImageURI() = postList[position].imgUsuario
        holder.txvDescricao.text = StringBuilder(postList[position].descricao.substring(0,20))
                .append("...").toString()
    }

}