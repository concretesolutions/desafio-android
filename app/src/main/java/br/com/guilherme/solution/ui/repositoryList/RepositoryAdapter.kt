package br.com.guilherme.solution.ui.repositoryList

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.guilherme.solution.R
import br.com.guilherme.solution.models.Repository
import com.squareup.picasso.Picasso

class RepositoryAdapter(
    val context: Context,
    val repositories: MutableList<Repository>,
    activity: Activity
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = activity as onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repositories.get(position)

        holder.textViewRepoTitle.setText(repo.name)
        holder.textViewRepoDescription.setText(repo.description)
        holder.textViewForks.setText(repo.forks)
        holder.textViewStars.setText(repo.stargazers_count)
        holder.textViewUserName.setText(repo.owner.login)

        Picasso.get().load(repo.owner.avatar_url).into(holder.imageViewAvatar)

        holder.linearLayout!!.setOnClickListener {
            listener.itemDetail(repo)
        }
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var linearLayout = itemView.findViewById<LinearLayout>(R.id.linear_layout)
        var textViewRepoTitle = itemView.findViewById<TextView>(R.id.text_view_repo_title)
        var textViewRepoDescription =
            itemView.findViewById<TextView>(R.id.text_view_repo_description)
        var textViewForks = itemView.findViewById<TextView>(R.id.text_view_forks)
        var textViewStars = itemView.findViewById<TextView>(R.id.text_view_stars)
        var textViewUserName = itemView.findViewById<TextView>(R.id.text_view_username)
        var imageViewAvatar = itemView.findViewById<ImageView>(R.id.image_view_avatar)
    }

    interface onItemClickListener {
        fun itemDetail(repository: Repository)
    }
}