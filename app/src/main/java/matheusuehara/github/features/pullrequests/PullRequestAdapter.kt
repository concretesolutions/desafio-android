package matheusuehara.github.features.pullrequests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lv_item_pull_request.view.*
import matheusuehara.github.R
import matheusuehara.github.data.model.PullRequest
import java.util.*

class PullRequestAdapter(var pullRequests: ArrayList<PullRequest>, var pullRequestClickListener: PullRequestClickListener) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var mPullRequestTitle: TextView = view.pull_request_title
        var mPullRequestBody: TextView = view.pull_request_body
        var mPullRequestUserName: TextView = view.pull_request_username
        var mPullRequestDate: TextView = view.pull_request_date
        var mPullRequestUseImage: ImageView = view.pull_request_user_image

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            pullRequestClickListener.onClick(pullRequests[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lv_item_pull_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = pullRequests[position]
        holder.mPullRequestTitle.text = "[${pullRequest.state.toUpperCase()}] ${pullRequest.title}"
        holder.mPullRequestBody.text = pullRequest.body
        holder.mPullRequestDate.text = pullRequest.created_at
        holder.mPullRequestUserName.text = pullRequest.user.login
        Picasso.get().load(pullRequest.user.avatar_url).error(R.mipmap.ic_launcher).into(holder.mPullRequestUseImage)
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    fun addPullRequests( newPullRequests: ArrayList<PullRequest>){
        pullRequests.addAll(newPullRequests)
        pullRequests.sortByDescending { it.state}
        this.notifyDataSetChanged()
    }
}
