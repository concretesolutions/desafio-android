package cl.getapps.githubjavarepos.features.repopullrequests.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.getapps.githubjavarepos.R
import cl.getapps.githubjavarepos.features.repopullrequests.domain.model.PullRequestModel
import kotlinx.android.synthetic.main.pull_list_content.view.*

class PullRequestsRecyclerViewAdapter : RecyclerView.Adapter<PullRequestsRecyclerViewAdapter.ViewHolder>() {

    var values: MutableList<PullRequestModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pull_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.description.text = item.body
        holder.owner.text = item.user.login
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.pull_item_title
        val description: TextView = view.pull_item_description
        val owner: TextView = view.pull_item_author
    }
}
