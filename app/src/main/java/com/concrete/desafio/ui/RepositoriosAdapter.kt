package com.concrete.desafio.ui

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.concrete.desafio.R
import com.concrete.desafio.data.Repositorio
import com.concrete.desafio.ui.fragments.PullRequestDialogFragment
import kotlinx.android.synthetic.main.item_repositorio.view.*
import org.koin.dsl.module.applicationContext

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

    var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriosViewHolder {

        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repositorio, parent, false)

        return RepositoriosViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RepositoriosViewHolder, position: Int){

        holder.bindView(data[position], position)

        holder.view.setOnClickListener {

            val fm: FragmentManager? = (mContext as AppCompatActivity).supportFragmentManager
            val pullRequestDialogFragment: PullRequestDialogFragment = PullRequestDialogFragment.newInstance(data[position].nome, data[position].autor.login)
            pullRequestDialogFragment.show(fm!!, "pull_request_dialogFragment")
        }
    }


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