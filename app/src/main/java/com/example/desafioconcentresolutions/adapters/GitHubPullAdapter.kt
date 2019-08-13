package com.example.desafioconcentresolutions.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.desafioconcentresolutions.R
import com.example.desafioconcentresolutions.models.GitHubPull
import kotlinx.android.synthetic.main.gitpull_item_card.view.*
import java.text.SimpleDateFormat
import java.util.*

class GitHubPullAdapter(private val context: Context) : PagedListAdapter<GitHubPull, RecyclerView.ViewHolder>(GitHubPullDiff) {

    private var mListener: GitHubPullListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GitHubPullViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.gitpull_item_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gitHubPull = getItem(position)
        with(holder as GitHubPullViewHolder){
            gitHubPull?.let{
                title.text = it.title
                description.text = it.description
                userName.text = it.user.username
                card.setOnClickListener {view ->
                    mListener?.onGitPullClicked(it.html_url)
                }
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

                createdAt.text = it.created_at

                loadImage(context, it.user.avatar_url , avatar)
            }
        }
    }

    fun setListener(listener: GitHubPullListener){
        mListener = listener
    }

    private fun loadImage(context: Context, imageUrl:String, view: ImageView){
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions.centerCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }


    class GitHubPullViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userName = itemView.textView_gitPullItemCard_username
        val avatar = itemView.imageView_gitPullItemCard_avatar
        val description = itemView.textView_gitPullItemCard_description
        val title = itemView.textView_gitPullItemCard_title
        val card = itemView.constraintLayout_gitPull_item_card
        val createdAt = itemView.textView_gitPullItemCard_createdAt
    }

    interface GitHubPullListener{
        fun onGitPullClicked(htmlUrl:String)
    }

    companion object {
        val GitHubPullDiff = object : DiffUtil.ItemCallback<GitHubPull>() {
            override fun areItemsTheSame(oldItem: GitHubPull, newItem: GitHubPull): Boolean {
                return oldItem.user.username == newItem.user.username
            }

            override fun areContentsTheSame(oldItem: GitHubPull, newItem: GitHubPull): Boolean {
                return oldItem.user.username == newItem.user.username
            }

        }
    }
}