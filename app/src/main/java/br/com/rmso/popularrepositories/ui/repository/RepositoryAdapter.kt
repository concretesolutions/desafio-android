package br.com.rmso.popularrepositories.ui.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rmso.popularrepositories.utils.ListOnClickListener
import br.com.rmso.popularrepositories.R
import br.com.rmso.popularrepositories.model.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.description_repository.view.*
import kotlinx.android.synthetic.main.description_user.view.*

class RepositoryAdapter (private val listRepositories: List<Repository>, private val listOnClick: ListOnClickListener)
    : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        return ViewHolder(
            view,
            parent
        )
    }

    override fun getItemCount(): Int = listRepositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = listRepositories[position]
        holder.bind(repository)

        holder.itemView.setOnClickListener {
            listOnClick.onClick(position)
        }
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_repository, parent, false)) {

        //IRepository
        private var mNameRepositoryTextView = itemView.tv_name_repository
        private var mDescriptionTextView = itemView.tv_description
        private var mNumberForksTextView = itemView.tv_qtd_fork
        private var mNumberStarsTextView = itemView.tv_qtd_star

        //owner
        private var mProfileImageView = itemView.img_profile
        private var mUsernameTextView = itemView.tv_username

        fun bind(repository: Repository) {
            mNameRepositoryTextView?.text = repository.name
            mDescriptionTextView?.text = repository.description
            mNumberForksTextView?.text = repository.forks.toString()
            mNumberStarsTextView?.text = repository.stargazers_count.toString()
            mUsernameTextView?.text = repository.owner.login
            Picasso.get()
                .load(repository.owner.avatar_url)
                .placeholder(R.drawable.loader)
                .error(R.drawable.problem)
                .into(mProfileImageView)
        }
    }
}