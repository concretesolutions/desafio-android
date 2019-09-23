package br.com.cavreti.desafio_android.useCase.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cavreti.desafio_android.R
import br.com.cavreti.desafio_android.data.Repository
import br.com.cavreti.desafio_android.util.OnAdapterItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repository_list_adapter.view.*

class RepositoryListAdapter(private val list: List<Repository>,private val listener: OnAdapterItemClickListener) :
    RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository_list_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.setIsRecyclable(false)
        val item = list[position]

       holder.username.text =  item.owner.name
       holder.forkCount.text = item.forks.toString()
       holder.repositoryDescription.text = item.description
       holder.repositoryName.text = item.name
       holder.starCount.text = item.stars.toString()

      Picasso.get().load(item.owner.imageUrl).into(holder.userImage)

      holder.parentLayout.setOnClickListener {
          listener.onItemClick(item, position)
      }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val starCount = view.starCount!!
        val repositoryName = view.repositoryName!!
        val repositoryDescription = view.repositoryDescription!!
        val userImage = view.userImage!!
        val forkCount = view.forkCount!!
        val username = view.username!!
        val parentLayout = view.parentLayout!!
    }
}