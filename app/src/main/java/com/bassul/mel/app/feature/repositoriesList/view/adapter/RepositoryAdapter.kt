package com.bassul.mel.app.feature.repositoriesList.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.R
import com.bassul.mel.app.domain.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_repository_item.view.*

class RepositoryAdapter(
    private val context: Context,
    var items: MutableList<Item>,
    private val itemClickListener: (Item) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>(),
    AdapterRepoContract {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_repository_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.apply {
            name.text = item.name
            description.text = item.description
            stars.text = item.stargazers_count
            forks.text = item.forks_count
            nameOwner.text = item.owner.login
            Picasso.get().load(item.owner.avatar_url).into(avatarOwner)
        }

        if (position + 1 == items.size) {
            holder.changeVisibility(true)
        } else {
            holder.changeVisibility(false)
        }

        setTouchListener(holder)
        setClickListener(holder, item)
    }

    private fun setClickListener(holder: ViewHolder, item : Item){
        holder.clickableView.setOnClickListener {
            itemClickListener(item)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(holder: ViewHolder) =
        holder.clickableView.setOnTouchListener OnTouchListener@{ _, motionEvent: MotionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    holder.background.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorAccent
                        )
                    )
                }
                else -> {
                    holder.background.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.lightGray
                        )
                    )
                }
            }
            return@OnTouchListener false
        }

    override fun addItems(newItems: List<Item>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.itemRepoTextViewName!!
        val description = itemView.itemRepoTextViewDescription!!
        val avatarOwner = itemView.itemRepoImageViewAvatar
        val nameOwner = itemView.itemRepoTextViewOwnerName
        val stars = itemView.itemRepoTextViewStars
        val forks = itemView.itemRepoTextViewForks
        val progressBar = itemView.itemRepoProgressbar
        val starImage = itemView.itemRepoImageViewStars
        val forkImage = itemView.itemRepoImageViewForks
        val clickableView = itemView.itemRepoCardview
        val background = itemView.itemRepoLayoutbackground

        fun changeVisibility(isLastItem: Boolean) {
            if (isLastItem) {
                setVisibilityLoading(View.VISIBLE)
                setVisibilityItem(View.INVISIBLE)
            } else {
                setVisibilityLoading(View.INVISIBLE)
                setVisibilityItem(View.VISIBLE)
            }
        }

        private fun setVisibilityItem(visibility: Int) {
            name.visibility = visibility
            description.visibility = visibility
            avatarOwner.visibility = visibility
            nameOwner.visibility = visibility
            stars.visibility = visibility
            forks.visibility = visibility
            starImage.visibility = visibility
            forkImage.visibility = visibility
        }

        private fun setVisibilityLoading(visibility: Int) {
            progressBar.visibility = visibility
        }
    }

}


