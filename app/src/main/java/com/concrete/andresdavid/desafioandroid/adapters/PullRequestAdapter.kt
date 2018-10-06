package com.concrete.andresdavid.desafioandroid.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.concrete.andresdavid.desafioandroid.R
import com.concrete.andresdavid.desafioandroid.util.CircleTransform
import com.squareup.picasso.Picasso
import android.content.Intent
import android.net.Uri
import com.concrete.andresdavid.desafioandroid.model.PullRequest
import com.concrete.andresdavid.desafioandroid.util.Constants
import kotlinx.android.synthetic.main.pullrequest_list_item.view.*


class PullRequestAdapter(private val pullRequestList: MutableList<PullRequest>, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return pullRequestList.count()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constants.TYPE_PULL_REQUEST -> PullRequestHolder(LayoutInflater.from(context).inflate(R.layout.pullrequest_list_item, parent, false), context)
            Constants.TYPE_EMPTY -> EmptyPullRequestHolder(LayoutInflater.from(context).inflate(R.layout.empty_list_item, parent, false))
            else -> LoadingPullRequestHolder(LayoutInflater.from(context).inflate(R.layout.loading_list_item, parent, false))
        }
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            Constants.TYPE_PULL_REQUEST -> (holder as PullRequestHolder).onBind(pullRequestList[position])
            Constants.TYPE_LOADING -> (holder as LoadingPullRequestHolder).onBind()
            Constants.TYPE_EMPTY -> (holder as EmptyPullRequestHolder).onBind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            pullRequestList?.get(position)?.type == "LOADING" -> Constants.TYPE_LOADING
            pullRequestList?.get(position)?.type == "EMPTY" -> Constants.TYPE_EMPTY
            else -> Constants.TYPE_PULL_REQUEST
        }
    }

    fun pushItems(resultList: List<PullRequest>?) {
        val newResultList = ArrayList<PullRequest>()
        newResultList.addAll(this.pullRequestList!!)
        newResultList.addAll(resultList!!)
        this.pullRequestList.addAll(resultList)
    }

    fun pushItem(pullRequest: PullRequest?) {
        pullRequestList?.add(pullRequest!!)
        notifyItemInserted(pullRequestList!!.size - 1)
    }

    fun popItem() {
        pullRequestList?.removeAt(pullRequestList.size - 1)
        notifyItemRemoved(pullRequestList!!.size)
    }
}

class PullRequestHolder(val view: View, private val context: Context) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private var pullRequest: PullRequest? = null

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(this.pullRequest?.htmlUrl))
        context.startActivity(intent)
    }

    fun onBind(pullRequest: PullRequest) {
        this.pullRequest = pullRequest
        Picasso.get().load(pullRequest.user?.avatarUrl).transform(CircleTransform()).into(view.image_pr_user)

        view.tv_pr_title.text = pullRequest.title
        view.tv_pr_body.text = pullRequest.body
        view.tv_pr_user.text = pullRequest.user?.login
    }
}

class LoadingPullRequestHolder(v: View) : RecyclerView.ViewHolder(v){
    fun onBind() {
    }
}

class EmptyPullRequestHolder(v: View) : RecyclerView.ViewHolder(v){
    fun onBind() {
    }
}