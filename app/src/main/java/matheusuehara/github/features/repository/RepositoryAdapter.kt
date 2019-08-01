package matheusuehara.github.features.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lv_item_repository.view.*
import matheusuehara.github.R
import matheusuehara.github.data.model.Repository
import java.util.*

class RepositoryAdapter(var repositories: ArrayList<Repository>, var repositoryClickListener: RepositoryClickListener) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var mRepositoryUserImage: ImageView = view.repository_user_image
        var mRepositoryName: TextView = view.repository_name
        var mRepositoryUserName: TextView = view.repository_username
        var mRepositoryFullName: TextView = view.repository_full_name
        var mRepositoryDescription: TextView = view.repository_description
        var mRepositoryStar: TextView = view.star_value
        var mRepositoryFork: TextView = view.fork_value

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            repositoryClickListener.onClick(repositories[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lv_item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository: Repository = repositories[position]
        holder.mRepositoryName.text = repository.name
        holder.mRepositoryDescription.text = repository.description
        holder.mRepositoryStar.text = repository.stargazers_count.toString()
        holder.mRepositoryFork.text = repository.forks_count.toString()
        holder.mRepositoryUserName.text = repository.owner.login
        holder.mRepositoryFullName.text = ""
        Picasso.get().load(repository.owner.avatar_url).error(R.mipmap.ic_launcher).into(holder.mRepositoryUserImage)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    fun addRepositories(newRepositories: ArrayList<Repository>) {
        repositories.addAll(newRepositories)
        this.notifyDataSetChanged()
    }
}