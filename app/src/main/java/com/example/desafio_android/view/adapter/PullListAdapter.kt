package com.example.desafio_android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafio_android.R
import com.example.desafio_android.data.model.Item
import com.example.desafio_android.data.pullmodel.RepositoryPullsItem
import com.example.desafio_android.view.RepositoryCallback
import com.example.desafio_android.view.adapter.diffutil.PullListDiffUtil
import com.example.desafio_android.view.adapter.diffutil.RepositoryDiffUtil
import kotlinx.android.synthetic.main.concrete_pulls_item.view.*
import kotlinx.android.synthetic.main.concrete_repository_item.view.*

class PullListAdapter () :
    androidx.recyclerview.widget.ListAdapter <RepositoryPullsItem,
            PullListAdapter.PullsViewHolder>(PullListDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PullListAdapter.PullsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.concrete_pulls_item,
                parent,
                false)

        return PullsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PullListAdapter.PullsViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class PullsViewHolder(
        view : View
    ) : RecyclerView.ViewHolder(view){

        private val imageUser = view.iv1
        private val userName = view.nametv
        private val repositoryName = view.tv_tittle
        private val repositoryDesc = view.tv_desc
        private val userId = view.tv_user_id

        fun bind(item : RepositoryPullsItem){

            repositoryName.text = item.title
            repositoryDesc.text = item.body
            userName.text = item.user.login
            userId.text = item.user.id.toString()
            imageUser.load(item.user.avatar_url){
                crossfade(true)
                placeholder(R.drawable.ic_person)
            }

        }

    }

}