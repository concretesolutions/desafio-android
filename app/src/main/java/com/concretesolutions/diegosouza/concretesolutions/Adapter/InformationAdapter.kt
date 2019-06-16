package com.concretesolutions.diegosouza.concretesolutions.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.concretesolutions.diegosouza.concretesolutions.Model.InformationList
import com.concretesolutions.diegosouza.concretesolutions.R
import kotlinx.android.synthetic.main.information_lista.view.*
import java.text.SimpleDateFormat

class InformationAdapter(
    private val context: Context
) : Adapter<InformationAdapter.ViewHolder>() {

    private var lista: ArrayList<InformationList> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.information_lista, parent, false)
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

    fun setList(listaSet: ArrayList<InformationList>) {
        lista = listaSet
        notifyDataSetChanged()
    }

    fun addData(listaAdd: List<InformationList>) {
        this.lista.addAll(listaAdd)
        notifyDataSetChanged()
    }

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }

        fun bindView(lista: InformationList) {
            val title = itemView.item_title
            val description = itemView.item_description
            val avatar = itemView.item_avatar
            val full_name = itemView.item_nome_sobrenome
            val name = itemView.item_username
            val data = itemView.item_date

            title.text = lista.title
            description.text = lista.body
            full_name.text = lista.base.repo.full_name
            name.text = lista.user.login

            val format = SimpleDateFormat("dd/MM/yyy HH:mm:ss")
            data.text = format.format(lista.created_at)

            Glide.with(itemView.context)
                .load(lista.user.avatar_url)
                .circleCrop()
                .into(avatar)
        }
    }

}