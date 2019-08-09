package sergio.com.br.desafioandroid.ui.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_search_list.view.*
import sergio.com.br.desafioandroid.R
import sergio.com.br.desafioandroid.listeners.GitListListener
import sergio.com.br.desafioandroid.models.GitItemsModel
import sergio.com.br.desafioandroid.utils.Utils

class SearchListRecycleAdapter(
    val items: ArrayList<GitItemsModel>,
    val gitListListener: GitListListener,
    val context: Context
) :
    RecyclerView.Adapter<SearchListRecycleAdapter.ViewHolder>() {
    var hasStoppedPaging: Boolean = false;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search_list, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(items.get(position), context)

        if (position == items.size - 1 && !hasStoppedPaging) {
            gitListListener.onPaging()
        }

        holder.mainLayout.setOnClickListener(View.OnClickListener {
            gitListListener.onItemCliked(items.get(position))
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mainLayout: ConstraintLayout = itemView.mainLayout

        fun setup(item: GitItemsModel, context: Context) {
            Glide.with(context)
                .load(item.owner.avatarUrl)
                .apply(RequestOptions.circleCropTransform().placeholder(context.getDrawable(R.drawable.user_placeholder)))
                .into(itemView.userImage)

            itemView.userNameText.text = item.owner.userName
            itemView.gitRepositoryNameText.text = item.name
            itemView.gitRepositoryDescriptionText.text = item.description
            itemView.starNumberText.text = Utils.getFormatedNumber(item.starsCount)
            itemView.forkNumberText.text = Utils.getFormatedNumber(item.forksCount)
        }
    }
}