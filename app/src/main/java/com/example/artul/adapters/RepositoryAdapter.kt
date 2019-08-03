package com.example.artul.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.artul.concrete_desafio_android.R
import com.example.artul.models.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.find

class RepositoryAdapter : BaseAdapter {

    private var listRepository = ArrayList<Repository>()
    private var context: Context
    private var adapter: RepositoryAdapter

    constructor(context: Context, listRepository: ArrayList<Repository>): super(){

        this.context = context
        this.adapter = this
        this.listRepository = listRepository

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View? = convertView

        if(view == null){

            view = LayoutInflater.from(this.context).inflate(R.layout.list_repository, parent, false)

        }

        var item: Repository = getItem(position) as Repository

        val txtName = view?.find(R.id.txt_name) as TextView
        val txtDescription = view.find(R.id.txt_description) as TextView
        val txtForks = view.find(R.id.txt_forks) as TextView
        val txtStars = view.find(R.id.txt_stars) as TextView
        val txtUsername = view.find(R.id.txt_username) as TextView
        val imgLogin = view.find<ImageView>(R.id.img_login)

        Picasso.with(this.context).load(item.owner.avatar_url).into(imgLogin)

        txtName.text = item.name
        txtDescription.text = item.description
        txtForks.text = item.forks_count.toString()
        txtStars.text = item.stargazers_count.toString()
        txtUsername.text = item.owner.login

        return view

    }

    override fun getCount(): Int {
        return this.listRepository.size
    }

    override fun getItem(position: Int): Any {
        return this.listRepository.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}