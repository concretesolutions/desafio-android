package com.ruiderson.desafio_android.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ruiderson.desafio_android.R
import com.ruiderson.desafio_android.models.PullRequest

class PullAdapter : ListAdapter<PullRequest, PullAdapter.MyViewHolder>(ItemDiffUtil()) {

    private var list = arrayListOf<PullRequest>()

    fun loadItems(pulls: ArrayList<PullRequest>)
    {
        if(list.size > 0) {

            val itemCount = list.size
            list.clear()
            notifyItemRangeRemoved(0, itemCount)

        }
        pulls.let {
            list.addAll(pulls)
        }
        notifyItemRangeInserted(0, list.size)
    }


    fun addItems(pulls: ArrayList<PullRequest>)
    {
        val positionStart = list.size
        pulls.let {
            list.addAll(pulls)
        }
        notifyItemRangeInserted(positionStart, list.size)
    }

    fun getItems() : ArrayList<PullRequest>{

        return list
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: PullRequest) {

            val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
            val txtBody: TextView = itemView.findViewById(R.id.txtBody)
            val txtUsername: TextView = itemView.findViewById(R.id.txtUsername)
            val imgUser: ImageView = itemView.findViewById(R.id.imgUser)

            txtTitle.text = item.title
            txtBody.text = item.body
            txtUsername.text = item.user.login
            Glide.with(itemView.context)
                .load(item.user.avatar_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgUser)


            //Listener
            itemView.setOnClickListener {


                //Starts the Pull Request Activity
                //val intent = Intent(itemView.context, PullViewActivity::class.java)
                //intent.putExtra(IntentCode.PULL_EXTRA.value, item)
                //itemView.context.startActivity(intent)


                //Opens the URL on the Browser
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.html_url)
                itemView.context.startActivity(intent)


            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pull, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        holder.bindView(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class ItemDiffUtil : DiffUtil.ItemCallback<PullRequest>() {
        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem.id == newItem.id
        }
    }
}