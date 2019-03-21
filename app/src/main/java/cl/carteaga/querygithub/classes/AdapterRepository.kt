package cl.carteaga.querygithub.classes

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import cl.carteaga.querygithub.PullRequestActivity
import cl.carteaga.querygithub.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_repository.view.*

class AdapterRepository(var data: MutableList<Repository>) :
    RecyclerView.Adapter<AdapterRepository.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_repository))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(repository: Repository) {
            itemView.txtNameRepository.text = repository.name
            itemView.txtAuthorName.text = repository.author.name
            itemView.txtDescriptionRepository.text = repository.name
            itemView.txtDescriptionRepository.text = repository.description
            itemView.txtNumberForks.text = repository.numberForks.toString()
            itemView.txtNumberStarts.text = repository.numberStar.toString()
            Glide.with(itemView.context)
                .load(repository.author.urlPhotoAuthor)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.imgAvatar)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PullRequestActivity::class.java)
                intent.putExtra("nameRepository", it.txtNameRepository.text)
                intent.putExtra("authorName", it.txtAuthorName.text)
                itemView.context.startActivity(intent)
            }
        }
    }

    fun add(repository: Repository)
    {
        data.add(repository)
        notifyItemInserted(data.size - 1)
    }

    fun add(repositories: List<Repository>)
    {
        for(repository in repositories)
        {
            add(repository)
        }
    }
}