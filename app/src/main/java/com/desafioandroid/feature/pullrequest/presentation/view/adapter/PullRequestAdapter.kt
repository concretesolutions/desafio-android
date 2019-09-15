package com.desafioandroid.feature.pullrequest.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.desafioandroid.R
import com.desafioandroid.core.util.*
import com.desafioandroid.data.model.pullrequest.entity.PullRequestResponse
import kotlinx.android.synthetic.main.row_data_pull_request.view.*
import java.util.*

class PullRequestAdapter(private var listPullRequestResponse: ArrayList<PullRequestResponse>) :
    RecyclerView.Adapter<PullRequestAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.row_data_pull_request, p0, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = listPullRequestResponse.size

    override fun onBindViewHolder(holder: ItemViewHolder, p1: Int) {

        val dataItem = listPullRequestResponse[p1]
        holder.bindView(dataItem)
    }

    fun clear(listPullRequestResponse: ArrayList<PullRequestResponse>) {
        this.listPullRequestResponse.clear()
        this.listPullRequestResponse.addAll(listPullRequestResponse)
        notifyDataSetChanged()
    }

    class ItemViewHolder(private val view: View):
        RecyclerView.ViewHolder(view) {

        private val titlePullRequest: TextView = view.text_title
        private val description: TextView = view.text_description
        private val nameUser: TextView = view.text_name_user
        private val createdData: TextView = view.text_created_date
        private val imageAvatar: ImageView = view.image_avatar

        fun bindView(pullRequestResponse: PullRequestResponse) = with(view) {
            titlePullRequest.text = pullRequestResponse.title
            description.textEmptyGone(pullRequestResponse.body)

            nameUser.text = pullRequestResponse.user.login
            createdData.text = pullRequestResponse.createdAt.dateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

            imageAvatar.fadeAnimation()

            val drawableImageDefault = R.drawable.icon_github_avatar_preview

            Glide.with(context)
                .load(pullRequestResponse.user.avatarUrl)
                .placeholder(drawableImageDefault)
                .error(drawableImageDefault)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageAvatar)
        }
    }
}