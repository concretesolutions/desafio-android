package br.com.rmso.popularrepositories.ui.pullrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rmso.popularrepositories.utils.ListOnClickListener
import br.com.rmso.popularrepositories.R
import br.com.rmso.popularrepositories.model.PullRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullResquestAdapter (private val listPullRequests: List<PullRequest>, private val listOnClick: ListOnClickListener)
    : RecyclerView.Adapter<PullResquestAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return ViewHolder(
            view,
            parent
        )
    }

    override fun getItemCount(): Int = listPullRequests.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = listPullRequests[position]
        holder.bind(pullRequest)

        holder.itemView.setOnClickListener {
            listOnClick.onClick(position)
        }
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pull_request, parent, false)) {

        private var mTitlePRTextView = itemView.tv_title_pull_request
        private var mBodyPRTextView = itemView.tv_body_pull_request
        private var mProfilePRImageView = itemView.img_profile_pull_request
        private var mUsernamePRTextView = itemView.tv_username_pull_request
        private var mDatePRTextView = itemView.tv_date_pull_request

        fun bind(pullRequest: PullRequest) {
            mTitlePRTextView?.text = pullRequest.title
            mBodyPRTextView?.text = pullRequest.body
            mUsernamePRTextView?.text = pullRequest.owner.login
            mDatePRTextView?.text = pullRequest.created_at
            Picasso.get()
                .load(pullRequest.owner.avatar_url)
                .placeholder(R.drawable.loader)
                .error(R.drawable.problem)
                .into(mProfilePRImageView)
        }
    }
}