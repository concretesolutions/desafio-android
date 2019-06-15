package com.abs.javarepos.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abs.javarepos.R
import com.abs.javarepos.model.PullRequest
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pull_request.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PullRequestAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    private val items = ArrayList<PullRequest>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(items: ArrayList<PullRequest>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View, private val onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(pullRequest: PullRequest) {
            itemView.apply {
                setOnClickListener { onItemClickListener.onItemClick(pullRequest) }

                tvTitle.text = pullRequest.title
                tvBody.text = pullRequest.body
                tvOwnerName.text = pullRequest.owner.login

                Glide.with(context)
                    .load(pullRequest.owner.avatarUrl)
                    .into(ivOwnerPicture)

                tvDate.text = formatDate(pullRequest.date)
            }
        }

        private fun formatDate(date: String): String {
            val formatter = SimpleDateFormat(itemView.context.getString(R.string.oldDatePattern), Locale.getDefault())
            val oldDate = formatter.parse(date)
            formatter.applyPattern(itemView.context.getString(R.string.newDatePattern))
            return formatter.format(oldDate)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pullRequest: PullRequest)
    }
}