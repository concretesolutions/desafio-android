package com.example.desafio_android.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.desafio_android.R
import com.example.desafio_android.pojo.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_repositories.view.*


class AdapterListRepositories(private var itens: ArrayList<Item>, private val activity: Activity): BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = activity.layoutInflater.inflate(R.layout.list_repositories, parent, false)
        val repositorie = getItem(position)

        view.txtNomeRepositorio.text = repositorie.name
        view.txtDescRepositorio.text = repositorie.description
        view.txtNomeSobrenome.text = repositorie.name
        view.txtUsername.text = repositorie.owner?.login ?: ""
        view.txtNumerFork.text = repositorie.forksCount.toString()
        view.txtStars.text = repositorie.stargazersCount.toString()
        Picasso.get().load(repositorie.owner?.avatarUrl).into(view.imageAvatar)


        return view
    }

        override fun getItem(position: Int): Item {
        return itens[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return itens.size
    }

    fun setItens(lItens: ArrayList<Item>){
        itens.addAll(lItens)
    }
}