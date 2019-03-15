package cl.getapps.githubjavarepos.features.repos.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.getapps.githubjavarepos.R
import cl.getapps.githubjavarepos.core.extension.loadFromUrl
import cl.getapps.githubjavarepos.features.repopullrequests.ui.PullRequestsActivity
import cl.getapps.githubjavarepos.features.repos.domain.model.RepoModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.repo_list_content.view.*

class ReposRecyclerViewAdapter :
    RecyclerView.Adapter<ReposRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    var values: MutableList<RepoModel> = mutableListOf()

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as RepoModel
            val intent = Intent(v.context, PullRequestsActivity::class.java).apply {
                putExtra(PullRequestsActivity.ARGS.REPO_NAME, item.name)
                putExtra(PullRequestsActivity.ARGS.REPO_OWNER, item.ownerModel.login)
            }
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.owner.text = item.ownerModel.login
        holder.title.text = item.name
        holder.description.text = item.description
        holder.forks.text = item.forks.toString()
        holder.starts.text = item.stargazers_count.toString()
        holder.ownerAvatar.loadFromUrl(item.ownerModel.avatarUrl)

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val owner: TextView = view.repo_item_author
        val title: TextView = view.repo_item_title
        val description: TextView = view.repo_item_description
        val forks: TextView = view.repo_item_forks
        val starts: TextView = view.repo_item_stars
        val ownerAvatar: CircleImageView = view.repo_item_image
    }
}
