package contarini.com.desafio_tembici.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import contarini.com.desafio_tembici.R
import contarini.com.desafio_tembici.data.models.RepositoriesResponse
import kotlinx.android.synthetic.main.item_repository.view.*


class HomeAdapter(private val context: Context, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: ArrayList<RepositoriesResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_repository, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            holder.bind(list[position], onItemClickListener)
        }
    }

    fun setRepositories(repositoriesResponse: List<RepositoriesResponse>){
        list.addAll(repositoriesResponse)
    }

    interface OnItemClickListener{
        fun onItemClicked( item : RepositoriesResponse )
    }

    inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(item : RepositoriesResponse, onItemClickListerner: OnItemClickListener){

            itemView.apply {
                setOnClickListener {
                    onItemClickListerner.onItemClicked( item )
                }

                tvName.text = item.name
                tvDescription.text = item.description
                tvQtdFork.text = item.forks.toString()
                tvQtdStars.text = item.stargazers_count.toString()
                tvNameUser.text = item.owner.login

                Picasso.get().load(item.owner.avatar_url).placeholder(R.drawable.ic_user).into(ivUser)

            }
        }
    }
}