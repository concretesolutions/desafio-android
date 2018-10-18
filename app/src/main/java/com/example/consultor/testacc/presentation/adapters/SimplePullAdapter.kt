package com.example.consultor.testacc.presentation.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.consultor.testacc.R
import com.example.consultor.testacc.data.pojos.PullModel
import kotlinx.android.synthetic.main.item_pull_info.view.*
import com.example.consultor.testacc.utils.giveCustomFormat
import com.example.consultor.testacc.utils.setvalidText

class SimplePullAdapter(val context: Context, var pullList: MutableList<PullModel>) :
    RecyclerView.Adapter<SimplePullAdapter.SimplePullViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SimplePullViewHolder {
        val pView = LayoutInflater.from(context).inflate(R.layout.item_pull_info, p0, false)
        return SimplePullViewHolder(pView)
    }

    override fun getItemCount(): Int {

        return pullList.size
    }

    override fun onBindViewHolder(p0: SimplePullViewHolder, p1: Int) {
        p0.bindView(pullList[p1])

    }


    class SimplePullViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pullModel: PullModel) {
            itemView.cv_pull_name.text = pullModel.title
            itemView.cv_contributor_alias.text = pullModel.creationDate.giveCustomFormat()
            itemView.cv_pull_desc.text = pullModel.body.setvalidText()
            itemView.cv_contributor_name.text = pullModel.user.name
            Glide.with(itemView.context).load(pullModel.user.avatar).apply(RequestOptions().circleCrop())
                .into(itemView.cv_contributor_avatar)

            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(  pullModel.url)))
            }
        }

    }
}




