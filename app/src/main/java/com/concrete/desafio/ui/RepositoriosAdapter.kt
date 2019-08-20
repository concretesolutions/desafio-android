package com.concrete.desafio.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.concrete.desafio.R
import com.concrete.desafio.data.Repositorio
import kotlinx.android.synthetic.main.item_repositorio.view.*

class RepositoriosViewHolder(val view: View): RecyclerView.ViewHolder(view){

    fun bindView(item: Repositorio, position: Int){
        with(view){
            nome.text = item.nome
            descricao.text = item.descricao
            forks.text = item.forks.toString()
            estrelas.text = item.estrelas.toString()
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

class RepositoriosAdapter(val data: MutableList<Repositorio> = mutableListOf()):
    RecyclerView.Adapter<RepositoriosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repositorio, parent, false)

        return RepositoriosViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RepositoriosViewHolder, position: Int) =
            holder.bindView(data[position], position)

    fun add(item: Repositorio){
        data.add(item)
        notifyDataSetChanged()
    }

    fun remove(item: Repositorio){
        data.remove(item)
        notifyDataSetChanged()
    }

    fun add(itens: List<Repositorio>){
        data.clear()
        data.addAll(itens)
        notifyDataSetChanged()
    }
}