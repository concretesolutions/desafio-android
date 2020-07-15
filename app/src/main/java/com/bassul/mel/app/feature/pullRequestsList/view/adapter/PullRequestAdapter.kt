package com.bassul.mel.app.feature.pullRequestsList.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.R
import com.bassul.mel.app.domain.PullRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_pull_request_item.view.*

class PullRequestAdapter(
    private val context: Context,
    private var pr: MutableList<PullRequest>,
    private val itemClickListener: (PullRequest) -> Unit
) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>(),
    AdapterPullRequestContract {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_pull_request_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = pr[position]

        holder.apply {
            name.text = pullRequest.userName
            body.text = pullRequest.body
            nameOwner.text = pullRequest.userName
            Picasso.get().load(pullRequest.userAvatar).into(holder.avatarOwner)
            date.text = pullRequest.updated_at

        }

        setTouchListener(holder)
        setClickListener(holder, pullRequest)
    }

    private fun setClickListener(holder: ViewHolder, pullRequest: PullRequest) {
        holder.clickableView.setOnClickListener {
            itemClickListener(pullRequest)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(holder: ViewHolder) {
        holder.clickableView.setOnTouchListener OnTouchListener@{ _, motionEvent: MotionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    holder.background.setBackgroundColor(getColor(context, R.color.colorAccent))
                }
                else -> {
                    holder.background.setBackgroundColor(getColor(context, R.color.lightGray))
                }
            }
            return@OnTouchListener false
        }
    }

    override fun addItems(newItems: List<PullRequest>) {
        pr.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pr.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name = itemView.itemPullReqTextViewTitle!!
        var body = itemView.itemPullReqTextViewBody!!
        var avatarOwner = itemView.itemPullReqImageViewAvatar
        var nameOwner = itemView.itemPullReqTextViewOwnerName
        var date = itemView.itemPullReqTextViewDate
        var clickableView = itemView.itemPullReqCardview
        var background = itemView.itemPullReqBackground
    }

}


