package br.com.briziapps.desafioconcrete.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.briziapps.desafioconcrete.R
import br.com.briziapps.desafioconcrete.domain.retrofit.GitHubRepoPullObjects
import br.com.briziapps.desafioconcrete.utils.StringUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_recycler_repo_puuls.view.*

class AdapterRecyclerRepoPulls( private val repositorePulls:List<GitHubRepoPullObjects>,
                                private val clickListener:(String) -> Unit ) : RecyclerView.Adapter<AdapterRecyclerRepoPulls.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_repo_puuls, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repositorePulls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(repositorePulls[position])
        holder.cLReposPull.setOnClickListener {
            clickListener(repositorePulls[position].urlRepo.toString())
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tVPullName: TextView = itemView.tVPullName
        private val tVPullDescription: TextView = itemView.tVPullDescription
        private val iVPullUser: ImageView = itemView.iVPullUser
        private val tVPullUserName: TextView = itemView.tVPullUserName
        private val tVPullUserSurName: TextView = itemView.tVPullUserSurName
        private val tVDateCreated: TextView = itemView.tVDateCreated
        val cLReposPull: ConstraintLayout = itemView.cLReposPull


        fun bindView(repositorePull: GitHubRepoPullObjects){

            tVPullName.text = repositorePull.titleOfPull
            tVPullDescription.text = repositorePull.bodyOfPull

            tVPullUserName.text = repositorePull.user?.userName
            tVPullUserSurName.text = repositorePull.head?.label
            val data = StringUtils.formataDataHora(repositorePull.dateOfPull!!, itemView.context)
            tVDateCreated.text = itemView.context.getString(R.string.pull_date, data)

            Glide.with(itemView.context)
                .asBitmap()
                .load(repositorePull.user?.userGravatar)
                .apply(RequestOptions().circleCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iVPullUser)

        }

    }
}