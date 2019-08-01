package com.example.challengecskotlin

import android.content.Context
import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


open class RepoAdapter(context: Context) : RecyclerView.Adapter<ViewHolder> () {

    private val ITEM = 0
    private val LOADING = 1
    private var repoResults: MutableList<Repo> = mutableListOf()
    private var mContext: Context = context
    private var isLoadingAdded = false
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var viewHolder: ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ITEM -> viewHolder = getViewHolder(parent, inflater)
            LOADING -> {
                val v2 = inflater.inflate(R.layout.item_loading, parent, false)
                viewHolder = LoadingVH(v2)
            }
        }
        return viewHolder!!
    }

    private fun getViewHolder(parent: ViewGroup, inflater: LayoutInflater): ViewHolder {
        val viewHolder: ViewHolder
        val v1 = inflater.inflate(R.layout.repo_row, parent, false)
        viewHolder = RepoVH(v1)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return if (repoResults == null) 0 else repoResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val repo = repoResults[position]

        when (getItemViewType(position)) {
            ITEM -> {
                val repoVH = holder as RepoVH
                repoVH.name.text = repo.name
                //repoVH.description.text = repo.description
                repoVH.login.text = repo.description
                repoVH.forks.text = repo.forks
                repoVH.stars.text = repo.stargazers_count
                repoVH.username.text = repo.owner!!.login
                holder.itemView.setOnClickListener {
                    d("onClick", "clicado: $repo")
                    val intent = Intent(mContext, PullRequestActivity::class.java)
                    intent.putExtra("login", repo.owner.login)
                    intent.putExtra("repositoryName", repo.name)
                    mContext.startActivity(intent)
                }

                Glide.with(mContext)
                     .asBitmap()
                     .load(repo.owner.avatar_url)
                     .into(holder.photo)

            }
            LOADING -> {
            }
        }// Do nothing
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == repoResults.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    protected inner class RepoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val login: TextView = itemView.findViewById<View>(R.id.login) as TextView
        val name: TextView = itemView.findViewById<View>(R.id.name) as TextView
        val forks: TextView = itemView.findViewById<View>(R.id.forks) as TextView
        val stars: TextView = itemView.findViewById<View>(R.id.num_stars) as TextView
        val photo: CircleImageView = itemView.findViewById(R.id.photo) as CircleImageView
        val username: TextView = itemView.findViewById(R.id.username) as TextView
    }


    protected inner class LoadingVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun add(r: Repo) {
        repoResults.add(r)
        notifyItemInserted(repoResults.size - 1)
    }

    fun addAll(moveResults: List<Repo>) {
        for (result in moveResults) {
            add(result)
        }
    }
    

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(Repo())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = repoResults.size - 1
        val result = getItem(position)

        if (result != null) {
            repoResults.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    private fun getItem(position: Int): Repo {
        return repoResults[position]
    }
}
