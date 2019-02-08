package com.accenture.githubrepositories.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.accenture.githubrepositories.MainActivity
import com.accenture.githubrepositories.R
import com.accenture.githubrepositories.fragments.PRFragmentWebView
import com.accenture.githubrepositories.pojo.PullRequest
import com.squareup.picasso.Picasso

class PullRequestsAdapter (context: Context) : RecyclerView.Adapter<PullRequestsAdapter.PullRequestsViewHolder>() {

    private var contextActivity: Context = context
    private var pullReqMutableList : MutableList<PullRequest> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PullRequestsViewHolder(layoutInflater.inflate(R.layout.pull_req_fragment_card_view, parent, false))
    }

    override fun getItemCount(): Int {
        return pullReqMutableList.size
    }

    override fun onBindViewHolder(holder: PullRequestsViewHolder, position: Int) {
        holder.bindModel(pullReqMutableList[position])
    }

    fun setPullRequests(data: List<PullRequest>){
        pullReqMutableList.clear()
            pullReqMutableList.addAll(data)
    }


    inner class PullRequestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rowView: CardView = itemView.findViewById(R.id.linearPullReqCardView)
        private val pullReqTitle: TextView = itemView.findViewById(R.id.pullReqTitle)
        private val pullReqDescriptionBody: TextView = itemView.findViewById(R.id.pullReqDescriptionBody)
        private val pullReqAvatarUser: ImageView = itemView.findViewById(R.id.pullReqAvatarUser)
        private val pullReqUserName: TextView = itemView.findViewById(R.id.pullReqUserName)
        private val pullReqCreatedDate: TextView = itemView.findViewById(R.id.pullReqCreatedDate)

        fun bindModel(pullRequest: PullRequest) {

            try {
                pullReqTitle.text = pullRequest.title
                pullReqDescriptionBody.text = pullRequest.body
                Picasso.get().load(pullRequest.user.avatar_url).into(pullReqAvatarUser)
                pullReqUserName.text = pullRequest.user.login
                pullReqCreatedDate.text = pullRequest.created_at

                rowView.setOnClickListener {

                    try {
                        // Get the text fragment instance
                        val pullRequestWebViewFragment = PRFragmentWebView.newInstance(pullRequest.html_url)

                        (contextActivity as MainActivity).supportFragmentManager.beginTransaction()
                                .add(R.id.mainFrameLayout, pullRequestWebViewFragment, "PulLRequestWebFragment")
                                .addToBackStack("PullRequestListFragment").commit()

                    }catch (e : IllegalStateException){

                        Toast.makeText(itemView.context, contextActivity.getString(R.string.generalError), Toast.LENGTH_LONG).show()
                    }
                }

            }catch (e : Exception){throw e}


        }

    }

}