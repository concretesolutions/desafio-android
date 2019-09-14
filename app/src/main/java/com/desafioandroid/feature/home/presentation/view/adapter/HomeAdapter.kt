package com.desafioandroid.feature.home.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.desafioandroid.R
import com.desafioandroid.core.util.*
import com.desafioandroid.data.model.home.entity.Item
import kotlinx.android.synthetic.main.row_data.view.*
import java.util.*

class HomeAdapter(private var listItem: ArrayList<Item>,
                  private val onItemClickListener: ((dataItem: Item) -> Unit)) :
    RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.row_data, p0, false)
        return ItemViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, p1: Int) {

        val dataItem = listItem[p1]
        holder.bindView(dataItem)
    }

    class ItemViewHolder(private val view: View, private val onItemClickListener: ((dataItem: Item) -> Unit)):
        RecyclerView.ViewHolder(view) {

        private val nameRepository: TextView = view.text_name_repository
        private val description: TextView = view.text_description
        private val countFork: TextView = view.text_count_fork
        private val countStar: TextView = view.text_count_star
        private val nameUser: TextView = view.text_name_user
        private val createdDate: TextView = view.text_created_date
        private val imageAvatar: ImageView = view.image_avatar

        fun bindView(dataItem: Item) = with(view) {
            nameRepository.text = dataItem.fullName
            description.text = dataItem.description
            countFork.text = dataItem.forksCount.decimalFormat()
            countStar.text = dataItem.stargazersCount.decimalFormat()
            nameUser.text = dataItem.owner.login
            createdDate.text = dataItem.createdAt.dateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

            imageAvatar.fadeAnimation()

            val drawableImageDefault = R.drawable.icon_github_logo_preview

            Glide.with(context)
                .load(dataItem.owner.avatarUrl)
                .placeholder(drawableImageDefault)
                .error(drawableImageDefault)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageAvatar)

            this.setOnClickListener {
                onItemClickListener.invoke(dataItem)
            }
        }
    }
}