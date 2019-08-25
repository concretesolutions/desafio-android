package br.com.briziapps.desafioconcrete.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.briziapps.desafioconcrete.R
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoObjects
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_recycler_repos.view.*

class AdapterRecyclerRepos( private val repositories:List<GitHubRepoObjects> ,
                            private val clickListener:(String, String) -> Unit
            ) : RecyclerView.Adapter<AdapterRecyclerRepos.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_repos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(repositories[position])
        holder.cLRepositorie.setOnClickListener {
            clickListener( repositories[position].repositorieName.toString(), repositories[position].repositorieFullName.toString())
        }

    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val tVRepoName: TextView = itemView.tVRepoName
        private val tVRepoDescription: TextView = itemView.tVRepoDescription
        private val tVUserName: TextView = itemView.tVUserName
        private val tVUserSurName:TextView = itemView.tVUserSurName

        private val tVForkCount:TextView = itemView.tVForkCount
        private val tVStarCount:TextView = itemView.tVStarCount

        private val iVUser:ImageView = itemView.iVUser

        //for click listener
        val cLRepositorie:ConstraintLayout = itemView.cLRepositorie

        fun bindView(repositorie: GitHubRepoObjects){

            tVRepoName.text = repositorie.repositorieName
            tVRepoDescription.text = repositorie.repositorieDescription
            tVUserName.text = repositorie.owner?.ownerName
            tVUserSurName.text = repositorie.repositorieFullName

            tVForkCount.text = repositorie.forksCount.toString()
            tVStarCount.text = repositorie.starsCount.toString()

            Glide.with(itemView.context)
                .asBitmap()
                .load(repositorie.owner?.ownerAvatar)
                .apply(RequestOptions().circleCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iVUser)

        }

    }
}