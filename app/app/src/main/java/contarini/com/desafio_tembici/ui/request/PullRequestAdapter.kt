package contarini.com.desafio_tembici.ui.request

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import contarini.com.desafio_tembici.R
import contarini.com.desafio_tembici.data.models.PullRequestResponse
import kotlinx.android.synthetic.main.item_pull_request.view.*


class PullRequestAdapter(private val context: Context, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: ArrayList<PullRequestResponse> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pull_request, parent, false)
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

    fun setRepositories(item: List<PullRequestResponse>){
        list.addAll(item)
    }

    interface OnItemClickListener{
        fun onItemClicked( item : PullRequestResponse )
    }

    inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(item : PullRequestResponse, onItemClickListerner: OnItemClickListener){

            itemView.apply {
                setOnClickListener {
                    onItemClickListerner.onItemClicked( item )
                }

                tvNamePull.text = item.title
                tvDescriptionPull.text = item.body
                Picasso.get().load(item.user.avatar_url).placeholder(R.drawable.ic_user).into(ivUserPull)
                tvNameUserPull.text = item.user.login

            }
        }
    }
}