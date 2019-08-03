package br.edu.ifsp.scl.desafio_android.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.edu.ifsp.scl.desafio_android.R
import br.edu.ifsp.scl.desafio_android.model.Repository
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.item_repositories_list.view.*

class RepositoriesAdapter(private val context: Context
                          , private val repository: HashSet<Repository>
                          , val listener: (Int) -> Unit) : RecyclerView.Adapter<RepositoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepositoryVH(LayoutInflater.from(parent.context), parent)
    override fun onBindViewHolder(holder: RepositoryVH, position: Int) = holder.bind(repository.elementAt(position)!!, position, listener)
    override fun getItemCount(): Int = repository.size!!

    /*Helpers - Pagination*/
    fun add(r: Repository) {
        repository.add(r)
        notifyItemInserted(itemCount - 1)
    }
    fun addAll(ps: HashSet<Repository>) = ps?.forEach { add(it!!) }
    fun remove(p: Repository) {
        var position  = repository.indexOf(p) as Int
        if (position > -1) {
            repository.remove(p)
            notifyItemRemoved(position)
        }
    }
}

class RepositoryVH(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_repositories_list, parent, false)) {
    private var ivAvatar: ImageView? = null
    private var tvStarFork: TextView? = null
    private var tvDescription: TextView? = null
    private var tvTitle: TextView? = null

    init {
        ivAvatar = itemView.repository_poster
        tvStarFork = itemView.repository_fork_star
        tvDescription = itemView.repository_desc
        tvTitle = itemView.repository_title
    }

    fun bind(repository: Repository, pos: Int, listener: (Int) -> Unit) = with(itemView) {

        Glide.with(this)
            .asBitmap()
            .load(repository.owner.avatar_url)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(e: GlideException?,model: Any?,target: Target<Bitmap>?,isFirstResource: Boolean): Boolean {
                    repository_progress.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(resource: Bitmap?,model: Any?,target: Target<Bitmap>?,dataSource: DataSource?,isFirstResource: Boolean): Boolean {
                    repository_progress.visibility = View.GONE
                    return false
                }
            })
            .into(ivAvatar!!)


        tvStarFork?.text = " ${repository.stargazers_count} | ${repository.forks_count} "
        tvDescription?.text = "${repository.description}"
        tvTitle?.text = "${repository.name}"

        layout_item_repository_list.setOnClickListener {
            listener(pos)
        }
    }
}