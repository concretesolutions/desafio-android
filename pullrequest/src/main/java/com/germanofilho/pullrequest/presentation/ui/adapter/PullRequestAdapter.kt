package com.germanofilho.pullrequest.presentation.ui.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.germanofilho.network.feature.pullrequestlist.model.entity.GitPullRequestResponse
import com.germanofilho.pullrequest.R
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestAdapter(private val onItemClickListener: ((String) -> Unit)) : RecyclerView.Adapter<ViewHolder>(){

    private var pullRequestList = mutableListOf<GitPullRequestResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = pullRequestList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pullRequestList[position])
    }

    fun addItem(carList: List<GitPullRequestResponse>){
        pullRequestList.addAll(carList)
        notifyDataSetChanged()
    }
}

class ViewHolder(private val view: View, private val onItemClickListener: ((String) -> Unit)): RecyclerView.ViewHolder(view){
    fun bind(item: GitPullRequestResponse){
        with(view){
            tv_pull_request_title.text = item.title
            tv_pull_request_description.text = HtmlCompat.fromHtml(item.body, HtmlCompat.FROM_HTML_MODE_LEGACY)
            tv_username.text = item.user.login

            Glide
                .with(view.context)
                .load(item.user.avatarUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .circleCrop()
                .into(iv_avatar)

            this.setOnClickListener {
                onItemClickListener.invoke(item.htmlUrl)
            }
        }
    }

}