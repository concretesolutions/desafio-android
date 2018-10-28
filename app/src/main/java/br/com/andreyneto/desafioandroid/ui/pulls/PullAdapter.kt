package br.com.andreyneto.desafioandroid.ui.pulls

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.desafioandroid.R
import br.com.andreyneto.desafioandroid.model.Pull
import br.com.andreyneto.desafioandroid.ui.components.loadImage
import kotlinx.android.synthetic.main.list_pull.view.*


class PullAdapter(private val pulls: List<Pull>,
                  private val ctx: Context,
                  private val presenter: PullsContract.Presenter) : RecyclerView.Adapter<PullAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PullAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.list_pull, p0, false), presenter)
    }

    override fun getItemCount(): Int {
        return pulls.size
    }

    override fun onBindViewHolder(p0: PullAdapter.ViewHolder, p1: Int) {
        p0.bindView(pulls[p1], pulls.size==p1+1)
    }

    class ViewHolder(itemView: View, val presenter: PullsContract.Presenter): RecyclerView.ViewHolder(itemView) {
        fun bindView(pull: Pull, last: Boolean) {
            itemView.lblPullTitle.text = pull.title
            itemView.lblPullDescription.text = pull.body
            itemView.lblOwner.text = pull.user.name
            itemView.separator.visibility = if(last) View.GONE else View.VISIBLE
            itemView.ownerPic.loadImage(pull.user.profilePic)
            itemView.isClickable = true
            itemView.setOnClickListener { presenter.openPull(pull.url, itemView.context) }
        }
    }
}