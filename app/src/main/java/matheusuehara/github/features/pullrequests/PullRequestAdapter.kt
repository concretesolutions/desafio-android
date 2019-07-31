package matheusuehara.github.features.pullrequests

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Transformation
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lv_item_pull_request.view.pull_request_title
import kotlinx.android.synthetic.main.lv_item_pull_request.view.pull_request_body
import kotlinx.android.synthetic.main.lv_item_pull_request.view.pull_request_username
import kotlinx.android.synthetic.main.lv_item_pull_request.view.pull_request_user_image

import java.util.ArrayList

import matheusuehara.github.R
import matheusuehara.github.data.model.PullRequest

class PullRequestAdapter(var pullRequests: ArrayList<PullRequest>, var context: Context) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    private val transformation:Transformation = RoundedTransformationBuilder()
            .cornerRadius(30F)
            .oval(false)
            .build()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var mPullRequestTitle:TextView = view.pull_request_title
        var mPullRequestBody:TextView = view.pull_request_body
        var mPullRequestUserName:TextView = view.pull_request_username
        var mPullRequestUseImage: ImageView = view.pull_request_user_image

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(pullRequests[adapterPosition].html_url)
            context.startActivity(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lv_item_pull_request, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = pullRequests[position]
        holder.mPullRequestBody.text = pullRequest.body
        holder.mPullRequestTitle.text = pullRequest.title
        holder.mPullRequestUserName.text = pullRequest.user.login

        Picasso.get()
                .load(pullRequest.user.avatar_url)
                .error(R.mipmap.ic_launcher)
                .transform(transformation)
                .into(holder.mPullRequestUseImage)
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }
}
