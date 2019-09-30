package br.com.guilherme.solution.ui.repositoryList

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.guilherme.solution.R
import br.com.guilherme.solution.models.Repository

class RepositoryAdapter(
    val context: Context,
    val repositories: MutableList<Repository>,
    activity: Activity
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = activity as onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repositories.get(position)

        /*holder.textViewTitle.setText(issue.title)
        holder.textViewEstado.setText(issue.state)

        holder.linearLayout!!.setOnClickListener {
            listener.itemDetail(issue)
        }*/
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        /*var linearLayout = itemView.findViewById<LinearLayout>(R.id.linear_layout)
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title)
        var textViewEstado = itemView.findViewById<TextView>(R.id.text_view_estado)*/
    }

    interface onItemClickListener {
        fun itemDetail(repository: Repository)
    }
}