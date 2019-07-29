package com.example.challengecskotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


open class RepoAdapter : RecyclerView.Adapter<ViewHolder> () {

    private val ITEM = 0
    private val LOADING = 1

    private var movieResults: MutableList<Repo> = mutableListOf()

    private var isLoadingAdded = false

    fun getMovies(): MutableList<Repo> {
        return movieResults
    }

    fun setMovies(movieResults: MutableList<Repo>) {
        this.movieResults = movieResults
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            ITEM -> viewHolder = getViewHolder(parent, inflater)
            LOADING -> {
                val v2 = inflater.inflate(R.layout.item_loading, parent, false)
                viewHolder = LoadingVH(v2)
            }
        }
        return viewHolder!!
    }

    private fun getViewHolder(parent: ViewGroup, inflater: LayoutInflater): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val v1 = inflater.inflate(R.layout.repo_row, parent, false)
        viewHolder = MovieVH(v1)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return if (movieResults == null) 0 else movieResults.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val repo = movieResults[position]

        when (getItemViewType(position)) {
            ITEM -> {
                val movieVH = holder as MovieVH
                movieVH.name.text = repo.name
                movieVH.description.text = repo.description
            }
            LOADING -> {
            }
        }// Do nothing
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == movieResults.size - 1 && isLoadingAdded) LOADING else ITEM
    }

//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val name: TextView = itemView.name
//        val description: TextView = itemView.description
//    }

//    private fun populateItemRows(holder: ViewHolder, position: Int) {
//        val repo = movieResults[position]
//        //holder.bind(repo.name, repo.description)
//        holder.name.text = repo.name
//        holder.description.text = repo.description
//
//        holder.itemView.setOnClickListener {
//            d("onClick", "clicado: $repo")
//        }
//    }

    protected inner class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById<View>(R.id.name) as TextView
        val description: TextView = itemView.findViewById<View>(R.id.description) as TextView
    }


    protected inner class LoadingVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun add(r: Repo) {
        movieResults.add(r)
        notifyItemInserted(movieResults.size - 1)
    }

    fun addAll(moveResults: List<Repo>) {
        for (result in moveResults) {
            add(result)
        }
    }

    fun remove(r: Repo) {
        val position = movieResults.indexOf(r)
        if (position > -1) {
            movieResults.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(Repo())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = movieResults.size - 1
        val result = getItem(position)

        if (result != null) {
            movieResults.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    private fun getItem(position: Int): Repo {
        return movieResults[position]
    }
}
