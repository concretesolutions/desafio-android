package com.example.desafio_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.R
import com.example.desafio_android.model.PullRequestModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class ListPullRequestsViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_pull_requests, parent, false)) {

    private var mTxtTituloPullRequest: TextView? = null
    private var mTxtDescPullRequest: TextView? = null
    private var mTxtcreatedAt: TextView? = null
    private var mTxtUsername: TextView? = null
    private var mImageAvatar: ImageView? = null


    init {
        mTxtTituloPullRequest = itemView.findViewById(R.id.txtTituloPullRequest)
        mTxtDescPullRequest = itemView.findViewById(R.id.txtDescPullRequest)
        mTxtcreatedAt = itemView.findViewById(R.id.txtCreatedAt)
        mTxtUsername = itemView.findViewById(R.id.txtUsername)
        mImageAvatar = itemView.findViewById(R.id.imageAvatar)
    }

    fun bind(pullRequestModel: PullRequestModel) {
        mTxtTituloPullRequest!!.text = pullRequestModel.name
        mTxtDescPullRequest!!.text = pullRequestModel.description
        mTxtcreatedAt!!.text = pullRequestModel.createdAt
        mTxtUsername!!.text = pullRequestModel.login ?: ""
        Picasso.
            get()
            .load(pullRequestModel.avatarUrl)
            .transform(CropCircleTransformation())
            .into(mImageAvatar)
    }
}
