package br.com.guilherme.solution.ui.pullRequests

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
import br.com.guilherme.solution.models.PullRequest
import com.squareup.picasso.Picasso

class PullRequestAdapter(
    val context: Context,
    val pulls: MutableList<PullRequest>,
    activity: Activity
) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = activity as onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pulls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pull = pulls.get(position)

        holder.textViewRepoTitle.setText(pull.title)
        holder.textViewRepoDescription.setText(pull.body)
        holder.textViewUserName.setText(pull.user.login)

        Picasso.get().load(pull.user.avatar_url).into(holder.imageViewAvatar)
        holder.imageViewStar.visibility = View.GONE
        holder.imageViewFork.visibility = View.GONE

        holder.linearLayout!!.setOnClickListener {
            listener.itemDetail(pull)
        }
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var linearLayout = itemView.findViewById<LinearLayout>(R.id.linear_layout)
        var textViewRepoTitle = itemView.findViewById<TextView>(R.id.text_view_repo_title)
        var textViewRepoDescription =
            itemView.findViewById<TextView>(R.id.text_view_repo_description)
        var textViewUserName = itemView.findViewById<TextView>(R.id.text_view_username)
        var imageViewAvatar = itemView.findViewById<ImageView>(R.id.image_view_avatar)
        var imageViewStar = itemView.findViewById<ImageView>(R.id.image_view_star)
        var imageViewFork = itemView.findViewById<ImageView>(R.id.image_view_fork)
    }

    interface onItemClickListener {
        fun itemDetail(pull: PullRequest)
    }
}