package com.rafaelmfer.consultinggithub.mvvm.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rafaelmfer.consultinggithub.R
import com.rafaelmfer.consultinggithub.mvvm.model.pullrequests.GitPullRequestResponse
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat

const val DATE_FROM_SERVER_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
const val TIME_PATTERN_HHh_MM = "HH'h'mm"
const val DATE_PATTERN_DD_MM_YY = "dd/MM/yy"

class PullRequestsListAdapter(var context: Context, var pullRequestList: List<GitPullRequestResponse>, private val listener: OnClickListenerGitHub) :
    RecyclerView.Adapter<PullRequestsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.pull_requests_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = pullRequestList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        pullRequestList[position].run {

            holder.apply {
                tvNamePullRequest.text = title
                tvDescriptionPullRequest.text = body
                tvUserNameLoginPull.text = user.login

                tvDateOfPull.text = context.getString(
                    R.string.time_and_date,
                    createdAt.formatFromServer(DATE_FROM_SERVER_PATTERN, TIME_PATTERN_HHh_MM),
                    createdAt.formatFromServer(DATE_FROM_SERVER_PATTERN, DATE_PATTERN_DD_MM_YY)
                )

                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .dontAnimate()
                    .into(civUserPullRequest)

                itemView.setOnClickListener {
                    listener.onClickOpenWeb(htmlUrl)
                }
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamePullRequest: TextView = itemView.findViewById(R.id.tvNamePullRequest)
        val tvDescriptionPullRequest: TextView = itemView.findViewById(R.id.tvDescriptionPullRequest)
        val tvUserNameLoginPull: TextView = itemView.findViewById(R.id.tvUserNameLoginPull)
        val tvDateOfPull: TextView = itemView.findViewById(R.id.tvDateOfPull)
        val civUserPullRequest: CircleImageView = itemView.findViewById(R.id.civUserPullRequest)
    }
}

@SuppressLint("SimpleDateFormat")
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.formatFromServer(serverPattern: String, pattern: String): String {
    val parse = SimpleDateFormat(serverPattern)
    val formatterDate = SimpleDateFormat(pattern)
    return formatterDate.format(parse.parse(this))
}