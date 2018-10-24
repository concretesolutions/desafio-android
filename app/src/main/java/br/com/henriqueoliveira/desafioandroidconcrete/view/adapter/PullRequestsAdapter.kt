package br.com.henriqueoliveira.desafioandroidconcrete.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.henriqueoliveira.desafioandroidconcrete.R
import br.com.henriqueoliveira.desafioandroidconcrete.helpers.loadUrl
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import kotlinx.android.synthetic.main.pull_request_item.view.*

typealias PRItemClickListener = (PullRequest) -> Unit

class PullRequestsAdapter(private var pullRequestItems: List<PullRequest>, private val listener: PRItemClickListener) : RecyclerView.Adapter<PullRequestsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pull_request_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateItem(pullRequestItems[position])
    }

    override fun getItemCount(): Int {
        return pullRequestItems.size
    }


    fun updateList(list: List<PullRequest>, normalUpdate: Boolean = true) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(old: Int, new: Int) = pullRequestItems[old].url == list[new].url
            override fun getOldListSize() = pullRequestItems.size
            override fun getNewListSize() = list.size
            override fun areContentsTheSame(old: Int, new: Int) = pullRequestItems[old].url == list[new].url
                    && normalUpdate
        })
        pullRequestItems = list.toMutableList()
        diff.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun populateItem(item: PullRequest) {
            item.let {
                itemView.title.text = item.title
                itemView.body.text = item.body
                itemView.username.text = item.user.login
                itemView.account.loadUrl(item.user.avatarUrl)
                itemView.setOnClickListener { listener.invoke(item) }

            }
        }


    }


}
