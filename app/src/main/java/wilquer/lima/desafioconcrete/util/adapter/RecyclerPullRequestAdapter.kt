package wilquer.lima.desafioconcrete.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pullrequest_item.view.*
import wilquer.lima.desafioconcrete.R
import wilquer.lima.desafioconcrete.data.model.PullRequest
import wilquer.lima.desafioconcrete.util.RecyclerClickListener

class RecyclerPullRequestAdapter(private val listPr: MutableList<PullRequest>,
                                 private val recyclerRepositoryClickListener: RecyclerClickListener) :
        RecyclerView.Adapter<RecyclerPullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pullrequest_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listPr.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.loadProfileImage(listPr[position].user.avatar_url)
        holder.txt_data_pr.text = listPr[position].created_at
        holder.txt_desc_pr.text = listPr[position].body
        holder.txt_repository_pr.text = listPr[position].title
        holder.txt_username_pr.text = listPr[position].user.login

        holder.itemView.setOnClickListener{
            recyclerRepositoryClickListener.onRecyclerClickListener(position)
        }

        val lenght = holder.txt_desc_pr.text.length
        if (lenght > 55) {
            holder.txt_desc_pr.text = listPr[position].body.replaceRange(55, lenght, "...")
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_repository_pr = itemView.txt_repository_pr
        val txt_desc_pr = itemView.txt_desc_pr
        val profile_photo = itemView.profile_photo
        val txt_username_pr = itemView.txt_username_pr
        val txt_data_pr = itemView.txt_data_pr

        fun loadProfileImage(url: String) {
            if (url.isBlank()) {
                Picasso.get()
                        .load(R.drawable.ic_user)
                        .placeholder(R.drawable.ic_user)
                        .error(R.drawable.ic_user)
                        .fit()
                        .into(profile_photo)
            } else {
                Picasso.get()
                        .load(url)
                        .placeholder(R.drawable.ic_user)
                        .error(R.drawable.ic_user)
                        .fit()
                        .into(profile_photo)
            }
        }
    }
}