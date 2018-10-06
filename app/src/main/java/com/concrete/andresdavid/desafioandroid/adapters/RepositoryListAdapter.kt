package com.concrete.andresdavid.desafioandroid.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.concrete.andresdavid.desafioandroid.R
import com.concrete.andresdavid.desafioandroid.model.Repository
import com.concrete.andresdavid.desafioandroid.util.CircleTransform
import com.concrete.andresdavid.desafioandroid.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repository_list_item.view.*
import android.content.Intent
import com.concrete.andresdavid.desafioandroid.MainActivity
import com.concrete.andresdavid.desafioandroid.PullRequestsActivity


class RepositoryAdapter(private val repositoryList: MutableList<Repository>, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return repositoryList.count()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constants.TYPE_REPOSITORY -> RepositoryHolder(LayoutInflater.from(context).inflate(R.layout.repository_list_item, parent, false), context)
            else -> LoadingRepositoryHolder(LayoutInflater.from(context).inflate(R.layout.loading_list_item, parent, false))
        }
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            Constants.TYPE_REPOSITORY -> (holder as RepositoryHolder).onBind(repositoryList[position])
            Constants.TYPE_LOADING -> (holder as LoadingRepositoryHolder).onBind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (repositoryList?.get(position)?.type == "LOADING") Constants.TYPE_LOADING else Constants.TYPE_REPOSITORY
    }

    fun pushItems(resultList: List<Repository>?) {
        val newResultList = ArrayList<Repository>()
        newResultList.addAll(this.repositoryList!!)
        newResultList.addAll(resultList!!)
        this.repositoryList.addAll(resultList)
    }

    fun pushItem(repo: Repository?) {
        repositoryList?.add(repo!!)
        notifyItemInserted(repositoryList!!.size - 1)
    }

    fun popItem() {
        repositoryList?.removeAt(repositoryList.size - 1)
        notifyItemRemoved(repositoryList!!.size)
    }
}

class RepositoryHolder(val view: View, private val context: Context) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private var repository: Repository? = null

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = Intent(this.context, PullRequestsActivity::class.java)
        intent.putExtra(MainActivity.REPOSITORY_NAME_KEY, this.repository?.name)
        intent.putExtra(MainActivity.REPOSITORY_FULL_NAME_KEY, this.repository?.fullName)
        context.startActivity(intent)
    }

    fun onBind(repository: Repository) {
        this.repository = repository
        Picasso.get().load(repository.owner?.avatarUrl).transform(CircleTransform()).into(view.image_user)

        //TODO("validate when image does not work or does not exist")

        view.tv_repository_name.text = repository.name
        view.tv_repository_description.text = repository.description
        view.tv_user_name.text = repository.owner?.login
        view.tv_stars_count.text = repository.stargazersCount.toString()
        view.tv_fork_count.text = repository.forksCount.toString()
    }
}

class LoadingRepositoryHolder(v: View) : RecyclerView.ViewHolder(v){
    fun onBind() {
    }
}