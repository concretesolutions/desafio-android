package com.bassul.mel.app.repositoriesList.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.AdapterItemsContract
import com.bassul.mel.app.AdapterPullRequestContract
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.R
import com.bassul.mel.app.domain.PullRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_repository_item.view.*

class PullRequestAdapter (private val context: Context, var pr : MutableList<PullRequest>, private val itemClickListener: (Item) -> Unit) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>(),
    AdapterPullRequestContract{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_pull_request_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = pr[position]

        holder?.name.text = pullRequest.userName
        holder?.body.text = pullRequest.body
        holder?.nameOwner.text = pullRequest.userName
        Picasso.get().load(pullRequest.userAvatar).into(holder?.avatarOwner)

        if(position + 1 == pr.size){
         //   holder.changeVisibility(true)
        }else{
        //    holder.changeVisibility(false)
        }
/*
        holder.clickableView.setOnClickListener {
            itemClickListener(item)
        }*/

    }

    override fun addItems(newItems: List<PullRequest>) {
        pr.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pr.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        var name = itemView.lr_tx_repository_name!!
        var body = itemView.lr_tx_repository_description!!
        var avatarOwner = itemView.lr_im_avatar
        var nameOwner = itemView.lr_tx_login
        var progressBar = itemView.lr_progressbar
        val clickableView = itemView.cardViewItem
/*
        fun changeVisibility(isLastItem : Boolean){
            if(isLastItem){
                setVisibilityLoading(View.VISIBLE)
                setVisibilityItem(View.INVISIBLE)
            }else{
                setVisibilityLoading(View.INVISIBLE)
                setVisibilityItem(View.VISIBLE)
            }
        }*/

   /*     private fun setVisibilityItem(visibility : Int) {
            name.visibility = visibility
            description.visibility = visibility
            avatarOwner.visibility = visibility
            nameOwner.visibility = visibility
            stars.visibility = visibility
            forks.visibility = visibility
            starImage.visibility = visibility
            forkImage.visibility = visibility
        }

        private fun setVisibilityLoading(visibility : Int){
            progressBar.visibility = visibility
        }*/

    }

}


