package br.com.concrete.githubconcretechallenge.features.repositories.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.concrete.githubconcretechallenge.R
import kotlinx.android.synthetic.main.cell_repository.view.*

/**
 * Created by georgemcjr on 2019-07-31.
 */
class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewFromLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_repository, parent, false)

        return ViewHolder(viewFromLayout)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind("George Cavalcanti")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: String) {
            itemView.tv_repository_name.text = data
        }

    }

}