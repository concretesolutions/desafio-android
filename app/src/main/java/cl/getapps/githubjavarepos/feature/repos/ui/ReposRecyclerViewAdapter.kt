package cl.getapps.githubjavarepos.feature.repos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.getapps.githubjavarepos.R
import cl.getapps.githubjavarepos.feature.repos.domain.Repo
import kotlinx.android.synthetic.main.item_list_content.view.*

class ReposRecyclerViewAdapter :
    RecyclerView.Adapter<ReposRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    var values: List<Repo>? = null

    init {
        onClickListener = View.OnClickListener { v ->
            /*val item = v.tag as Repo
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ItemDetailFragment.ARG_ITEM_ID, item.name)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    putExtra(ItemDetailFragment.ARG_ITEM_ID, item.name)
                }
                v.context.startActivity(intent)
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values?.get(position)
        holder.idView.text = item?.name
        holder.contentView.text = item?.description

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values?.size ?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
    }
}
