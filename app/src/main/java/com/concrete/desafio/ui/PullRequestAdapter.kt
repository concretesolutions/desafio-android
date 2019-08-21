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
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PullRequestViewHolder(val view: View): RecyclerView.ViewHolder(view){

    fun bindView(item: PullRequest, position: Int){
        with(view){
            titulo.text = item.titulo
            corpo.text = item.corpo
            autor.text = item.autor.login
            Glide.with(context).load(item.autor.avatar).placeholder(R.drawable.avatar).into(profile_image)

            var date = item.data.split("T")[0]
            var parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            var mes = parsedDate.monthValue.toString()
            if(mes.length == 1){
                mes = """0$mes"""
            }

            var dia = parsedDate.dayOfMonth.toString()
            if(dia.length == 1){
                dia = """0$dia"""
            }

            var dataFormatada = dia + "/" + mes + "/" + parsedDate.year.toString()
            data.text = dataFormatada
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
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(data[position].url)
            startActivity(mContext!!, i, null)

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