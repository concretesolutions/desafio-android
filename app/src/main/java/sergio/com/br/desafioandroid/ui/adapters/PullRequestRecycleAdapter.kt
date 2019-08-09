package sergio.com.br.desafioandroid.ui.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_pull_request_list.view.*
import kotlinx.android.synthetic.main.item_search_list.view.mainLayout
import kotlinx.android.synthetic.main.item_search_list.view.userImage
import kotlinx.android.synthetic.main.item_search_list.view.userNameText
import sergio.com.br.desafioandroid.R
import sergio.com.br.desafioandroid.listeners.PullRequestListListener
import sergio.com.br.desafioandroid.models.PullRequestModel
import sergio.com.br.desafioandroid.utils.Utils

class PullRequestRecycleAdapter(
    val items: ArrayList<PullRequestModel>,
    val pullRequestListListener: PullRequestListListener,
    val context: Context
) :
    RecyclerView.Adapter<PullRequestRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pull_request_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(items.get(position), context)

        holder.mainLayout.setOnClickListener(View.OnClickListener {
            pullRequestListListener.onItemCliked(items.get(position))
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mainLayout: ConstraintLayout = itemView.mainLayout

        fun setup(item: PullRequestModel, context: Context) {
            Glide.with(context)
                .load(item.user.avatarUrl)
                .apply(RequestOptions.circleCropTransform().placeholder(context.getDrawable(R.drawable.user_placeholder)))
                .into(itemView.userImage)

            itemView.userNameText.text = item.user.userName
            itemView.pullRequestNameText.text = item.title

            itemView.pullRequestDescriptionText.text =
                if (item.body.isBlank()) context.getString(R.string.no_description_text) else item.body

            itemView.createdAtText.text =
                "${context.getString(R.string.created_at_text)} ${Utils.formartDate(item.createdAt)}"
            itemView.updatedAtText.text =
                "${context.getString(R.string.updated_at_text)} ${Utils.formartDate(item.updatedAt)}"
        }
    }
}