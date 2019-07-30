package wilquer.lima.desafioconcrete.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wilquer.lima.desafioconcrete.R
import wilquer.lima.desafioconcrete.data.model.Repository

class RecyclerRepositoryAdapter(val listRepositories: List<Repository>, val context: Context) : RecyclerView.Adapter<RecyclerRepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item, viewType, false))
    }

    override fun getItemCount(): Int {
        return listRepositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}