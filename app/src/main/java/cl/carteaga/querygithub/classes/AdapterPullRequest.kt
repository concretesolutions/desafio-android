package cl.carteaga.querygithub.classes

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pull_request.view.*
import android.net.Uri
import cl.carteaga.querygithub.R
import cl.carteaga.querygithub.models.PullRequest
import com.bumptech.glide.load.engine.DiskCacheStrategy


class AdapterPullRequest(val data: List<PullRequest>?) :
    RecyclerView.Adapter<AdapterPullRequest.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AdapterPullRequest.ViewHolder(parent.inflate(R.layout.item_pull_request))
    }

    override fun getItemCount(): Int = data?.size?: 0

    override fun onBindViewHolder(holder: AdapterPullRequest.ViewHolder, position: Int) {
        holder.bindView(data?.get(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pullRequest: PullRequest?) {
            itemView.txtTitlePullRequest.text = pullRequest?.title
            itemView.txtBodyPullRequest.text = pullRequest?.body
            itemView.txtAuthorNamePullRequest.text = pullRequest?.user?.login
            Glide.with(itemView.context)
                .load(pullRequest?.user?.avatarUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.imgAvatarPullRequest)

            itemView.setOnClickListener {
                itemView.context
                    .startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(pullRequest?.htmlUrl
                            )
                        )
                    )
            }
        }
    }
}