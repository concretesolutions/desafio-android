package com.ruiderson.desafio_android.ui

import android.content.Intent
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
import com.ruiderson.desafio_android.PullActivity
import com.ruiderson.desafio_android.R
import com.ruiderson.desafio_android.enum.IntentCode
import com.ruiderson.desafio_android.models.Repository

class RepositoryAdapter : ListAdapter<Repository, RepositoryAdapter.MyViewHolder>(ItemDiffUtil()) {

    private var list = arrayListOf<Repository>()


    fun loadItems(repositories: ArrayList<Repository>)
    {
        if(list.size > 0) {

            val itemCount = list.size
            list.clear()
            notifyItemRangeRemoved(0, itemCount)

        }
        repositories.let {
            list.addAll(repositories)
        }
        notifyItemRangeInserted(0, list.size)
    }


    fun addItems(repositories: ArrayList<Repository>)
    {
        val positionStart = list.size
        repositories.let {
            list.addAll(repositories)
        }
        notifyItemRangeInserted(positionStart, list.size)
    }

    fun getItems() : ArrayList<Repository>{
        return list
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Repository) {

            val txtName: TextView = itemView.findViewById(R.id.txtName)
            val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
            val txtFork: TextView = itemView.findViewById(R.id.txtFork)
            val txtStar: TextView = itemView.findViewById(R.id.txtStar)
            val txtUsername: TextView = itemView.findViewById(R.id.txtUsername)
            val imgUser: ImageView = itemView.findViewById(R.id.imgUser)

            txtName.text = item.name
            txtDescription.text = item.description
            txtFork.text = item.forks.toString()
            txtStar.text = item.stargazers_count.toString()
            txtUsername.text = item.owner.login
            Glide.with(itemView.context)
                .load(item.owner.avatar_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgUser)


            //Listener
            itemView.setOnClickListener {


                //Starts the Pull Request Activity
                val intent = Intent(itemView.context, PullActivity::class.java)
                intent.putExtra(IntentCode.REPOSITORY_EXTRA.value, item)
                itemView.context.startActivity(intent)


            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        holder.bindView(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }


    class ItemDiffUtil : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            var same: Boolean = (oldItem.forks == newItem.forks)
            if(same){
                same = (oldItem.stargazers_count == newItem.stargazers_count)
            }
            return same
        }
    }
}