package cl.jesualex.desafio_android.repo.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.jesualex.desafio_android.R
import cl.jesualex.desafio_android.base.presentation.ItemAdapterListener
import cl.jesualex.desafio_android.repo.data.domain.entity.Pull
import cl.jesualex.desafio_android.repo.data.domain.entity.Repo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_pull.view.*

/**
 * Created by jesualex on 2019-05-30.
 */
class PullAdapter: RecyclerView.Adapter<PullAdapter.ViewHolder>() {
    var itemClickListener: ItemAdapterListener<Pull>? = null

    var pulls: List<Pull> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_pull, p0, false))
    }

    override fun getItemCount(): Int {
        return pulls.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.initView(pulls[p1], p1, pulls.isNotEmpty() && p1 == pulls.size -1)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal fun initView(pull: Pull, pos: Int, isLast: Boolean){
            itemView.name.text = pull.title
            itemView.description.text = pull.body

            pull.user?.let {
                itemView.username.text = it.login

                Glide.with(itemView)
                    .load(it.avatar_url)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .placeholder(android.R.drawable.spinner_background)
                    .into(itemView.avatarImage)

                if(it.name == null) {
                    itemView.userName.visibility = View.GONE
                }else{
                    itemView.userName.visibility = View.VISIBLE
                    itemView.userName.text = it.name
                }
            }

            itemView.separator.visibility = if(isLast) View.GONE else View.VISIBLE

            itemView.setOnClickListener {
                itemClickListener?.onItem(pull, pos)
            }
        }
    }
}