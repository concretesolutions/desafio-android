package wilquer.lima.desafioconcrete.util.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repository_item.view.*
import wilquer.lima.desafioconcrete.R
import wilquer.lima.desafioconcrete.data.model.Repository
import wilquer.lima.desafioconcrete.util.RecyclerClickListener

class RecyclerRepositoryAdapter(private val listRepositories: MutableList<Repository>,
                                private val context: Context,
                                private val recyclerRepositoryClickListener: RecyclerClickListener)
    : RecyclerView.Adapter<RecyclerRepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listRepositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.loadProfileImage(listRepositories[position].owner.avatar_url)
        holder.txt_repositoryName.text = listRepositories[position].name
        holder.txt_username.text = listRepositories[position].owner.login
        holder.txt_fullName.text = listRepositories[position].full_name
        holder.txt_fork.text = listRepositories[position].forks.toString()
        holder.txt_star.text = listRepositories[position].stargazers_count.toString()
        holder.txt_repositoryDesc.text = listRepositories[position].description

        holder.itemView.setOnClickListener {
            recyclerRepositoryClickListener.onRecyclerClickListener(position)
        }

        val lenght = holder.txt_repositoryDesc.text.length
        if (lenght > 55) {
            holder.txt_repositoryDesc.text = listRepositories[position].description.replaceRange(55, lenght, "...")
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_repositoryName = itemView.txt_repositoryName
        var txt_repositoryDesc = itemView.txt_repositoryDesc
        var txt_fork = itemView.txt_fork
        var txt_star = itemView.txt_star
        var profile_photo = itemView.profile_photo
        var txt_username = itemView.txt_username
        var txt_fullName = itemView.txt_fullName


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

