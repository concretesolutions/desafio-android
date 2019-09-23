package br.com.cavreti.desafio_android.useCase.repositoryPullRequests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cavreti.desafio_android.R
import br.com.cavreti.desafio_android.data.PullRequest
import br.com.cavreti.desafio_android.util.OnAdapterItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pull_request_list_adapter.view.*

class PullRequestListAdapter(private val list: List<PullRequest>, private val listener: OnAdapterItemClickListener, private val context : Context)
    : RecyclerView.Adapter<PullRequestListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pull_request_list_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val item = list[position]

        holder.title.text = item.title ?: ""
        holder.description.text = item.description ?: ""
        holder.username.text = item.user?.name ?: ""

        Picasso.get().load(item.user?.imageUrl ?: "").into(holder.userImage)

        holder.parentLayout.setOnClickListener {
            listener.onItemClick(item, position)
        }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val title = view.title!!
        val description = view.description!!
        val userImage = view.userImage!!
        val username = view.username!!
        val parentLayout = view.parentLayout!!
    }
}

