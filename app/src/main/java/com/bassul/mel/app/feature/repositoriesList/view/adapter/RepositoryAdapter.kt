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
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    AdapterRepoContract {

    companion object{
        private const val TYPE_LOADING = 0
        private const val TYPE_ITEM = 1
    }
    
    override fun onCreateViewHolder (
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            ViewHolderItem(LayoutInflater.from(context).inflate(R.layout.layout_repository_item, parent, false))
        } else {
            ViewHolderLoading(LayoutInflater.from(context)
                .inflate(R.layout.layout_repository_loading_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if(getItemViewType(position) == TYPE_ITEM){
                val item = items[position]

                val holder  = holder as ViewHolderItem
                holder.apply {
                    name.text = item.name
                    description.text = item.description
                    stars.text = item.stargazers_count
                    forks.text = item.forks_count
                    nameOwner.text = item.owner.login
                    Picasso.get().load(item.owner.avatar_url).into(avatarOwner)
                }
                setTouchListener(holder)
                setClickListener(holder, item)
            }
    }

    private fun setClickListener(holder: ViewHolderItem, item: Item) {
        holder.clickableView.setOnClickListener {
            itemClickListener(item)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(holder: ViewHolderItem) =
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

    override fun getItemViewType(position: Int): Int {
        return if (position + 1 == items.size) {
            TYPE_LOADING
        } else {
            TYPE_ITEM
        }
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolderLoading(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.itemRepoTextViewName!!
        val description = itemView.itemRepoTextViewDescription!!
        val avatarOwner = itemView.itemRepoImageViewAvatar
        val nameOwner = itemView.itemRepoTextViewOwnerName
        val stars = itemView.itemRepoTextViewStars
        val forks = itemView.itemRepoTextViewForks
        val clickableView = itemView.itemRepoCardview
        val background = itemView.itemRepoLayoutbackground
    }

}


