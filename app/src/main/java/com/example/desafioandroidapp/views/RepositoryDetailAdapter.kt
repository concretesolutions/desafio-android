package com.example.desafioandroidapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidapp.data.dto.Pull
import com.example.desafioandroidapp.databinding.PullsItemBinding
import com.squareup.picasso.Picasso

class RepositoryDetailAdapter(private val listener: PullsListener): RecyclerView.Adapter<RepositoryDetailAdapter.PullViewHolder>() {

    private var pullsList : ArrayList<Pull> = ArrayList()
    interface PullsListener{
        fun selectedItem(
            pull : Pull)
    }

    fun setPulls(newPullsList: List<Pull>){
        if (this.pullsList.isEmpty()){
            this.pullsList = newPullsList as ArrayList<Pull>
        }else{
            this.pullsList.addAll(newPullsList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PullsItemBinding.inflate(layoutInflater,parent,false)
        return PullViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PullViewHolder, position: Int) {
        holder.bind(this.pullsList[position], listener)
    }

    override fun getItemCount(): Int {
        return this.pullsList.size
    }

    class PullViewHolder(
        private val binding: PullsItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(pull: Pull, listener: PullsListener) {
            this.binding.title.text = pull.title
            this.binding.body.text = pull.body
            this.binding.ownerName.text = pull.user.login
            Picasso.get().load(pull.user.avatar_url).into(this.binding.imageView)
            this.binding.root.setOnClickListener{
                listener.selectedItem(pull)
            }
        }
    }
}