package com.bassul.mel.app.feature.repositoriesList.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_repository_item.view.*

class RepositoryAdapter (private val context: Context, var items : MutableList<Item>,  private val itemClickListener: (Item) -> Unit) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>(),
    AdapterItemsContract {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_repository_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder?.name.text = item.name
        holder?.description.text = item.description
        holder?.stars.text= item.stargazers_count
        holder?.forks.text= item.forks_count
        holder?.nameOwner.text = item.owner.login
        Picasso.get().load(item.owner.avatar_url).into(holder?.avatarOwner)

        if(position + 1 == items.size){
            holder.changeVisibility(true)
        }else{
            holder.changeVisibility(false)
        }

        holder.clickableView.setOnTouchListener OnTouchListener@{ view: View, motionEvent: MotionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    holder?.background.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorAccent
                        )
                    )
                }
                else -> {
                    holder?.background.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.lightGray
                        )
                    )
                }
            }
            return@OnTouchListener false
        }

        holder.clickableView.setOnClickListener {
            itemClickListener(item)
        }

    }

    override fun addItems(newItems: List<Item>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        var name = itemView.lr_tx_repository_name!!
        var description = itemView.lr_tx_repository_description!!
        var avatarOwner = itemView.lr_im_avatar
        var nameOwner = itemView.lr_tx_login
        var stars = itemView.lr_tx_starts
        var forks = itemView.lr_tx_forks
        var progressBar = itemView.lr_progressbar
        var starImage = itemView.lr_im_stars
        var forkImage = itemView.lr_im_forks
        val clickableView = itemView.lr_cardview_repository_item
        val background = itemView.lr_background

        fun changeVisibility(isLastItem : Boolean){
            if(isLastItem){
                setVisibilityLoading(View.VISIBLE)
                setVisibilityItem(View.INVISIBLE)
            }else{
                setVisibilityLoading(View.INVISIBLE)
                setVisibilityItem(View.VISIBLE)
            }
        }

        private fun setVisibilityItem(visibility : Int) {
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
        }

    }

}


