package com.carol.apirest.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.listaview_principal.view.*

class ItemViewHold (itemView: View) : RecyclerView.ViewHolder(itemView){

    val txvNomeUsuario = itemView.txvNomeUsuario
    val txvQtdFork = itemView.txvQtdFork
    val txvQtdStar = itemView.txvQtdStar
    val txvRepositorio = itemView.txvRepositorio
    val imvAutor = itemView.imvAutor
    val txvDescricao = itemView.txvDescricao
}