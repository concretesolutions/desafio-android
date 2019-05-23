package com.uharris.desafio_android.presentation.sections.pull

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uharris.desafio_android.R
import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.utils.CircleTransform
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestAdapter(private var pullRequests: MutableList<PullRequest>, private val listener: (PullRequest) -> Unit):
    RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false))
    }

    override fun getItemCount() = pullRequests.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(pullRequests[position], listener)

    fun setItems(pullRequestList: List<PullRequest>) {
        pullRequests.clear()
        pullRequests.addAll(pullRequestList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: PullRequest, listener: (PullRequest) -> Unit) = with(itemView) {
            nameTextView.text = item.user.login
            titleTextView.text = item.title
            bodyTextView.text = item.body

            Picasso.get().load(item.user.avatar).transform(CircleTransform()).into(userImageView)
            setOnClickListener { listener(item) }
        }
    }
}