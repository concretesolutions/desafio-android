package com.concretesolutions.diegosouza.concretesolutions.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.concretesolutions.diegosouza.concretesolutions.Model.ItemsLista
import com.concretesolutions.diegosouza.concretesolutions.R
import kotlinx.android.synthetic.main.item_lista.view.*

class ListaAdapter(
    private val context: Context
) : Adapter<ListaAdapter.ViewHolder>() {

    private var lista: ArrayList<ItemsLista> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lista = lista[position]

        holder.let {
            it.bindView(lista)
        }
    }

    fun setList(listaSet: ArrayList<ItemsLista>) {
        lista = listaSet
        notifyDataSetChanged()
    }

    fun addData(listaAdd: List<ItemsLista>) {
        this.lista.addAll(listaAdd)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(lista: ItemsLista) {
            val title = itemView.item_title
            val description = itemView.item_description
            val avatar = itemView.item_avatar
            val full_name = itemView.item_nome_sobrenome
            val name = itemView.item_username
            val star = itemView.item_star
            val fork = itemView.item_fork

            title.text = lista.name
            description.text = lista.description
            full_name.text = lista.full_name
            name.text = lista.owner.login
            star.text = lista.stargazers_count.toString()
            fork.text = lista.forks_count.toString()

            Glide.with(itemView.context)
                .load(lista.owner.avatar_url)
                .circleCrop()
                .into(avatar)
        }
    }

}