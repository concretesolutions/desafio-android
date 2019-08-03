package com.example.artul.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.artul.concrete_desafio_android.R
import com.example.artul.models.PullRequest
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import java.text.SimpleDateFormat

class PullRequestAdapter : BaseAdapter {

    private var context: Context
    private var listPull: ArrayList<PullRequest>
    private var pullRequestAdapter: PullRequestAdapter

    constructor(context: Context, listPull: ArrayList<PullRequest>): super(){

        this.context = context
        this.listPull = listPull
        this.pullRequestAdapter = this

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var view: View? = convertView

        if(view == null){

            view = LayoutInflater.from(this.context).inflate(R.layout.list_pull, parent, false)

        }

        var item: PullRequest = getItem(position) as PullRequest

        val txtTitle = view?.find<TextView>(R.id.txt_title)
        val txtBody = view?.find<TextView>(R.id.txt_body)
        val txtAuthor = view?.find<TextView>(R.id.txt_author)
        val txtDate = view?.find<TextView>(R.id.txt_date)
        val imgAuthor = view?.find<ImageView>(R.id.img_author)

        Picasso.with(this.context).load(item.user.avatar_url).into(imgAuthor)

        val dateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

        txtTitle?.text = item.title
        txtBody?.text = item.body
        txtDate?.text = dateFormat.format(item.created_at)
        txtAuthor?.text = item.user.login

        return view

    }

    override fun getCount(): Int {
        return this.listPull.size
    }

    override fun getItem(position: Int): Any {
        return this.listPull.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}