package com.accenture.githubrepositories.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.accenture.githubrepositories.R
import com.accenture.githubrepositories.fragments.PRFragment
import com.accenture.githubrepositories.pojo.Repository
import com.squareup.picasso.Picasso
import com.accenture.githubrepositories.MainActivity


class RepositoriesAdapter (context: Context) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {

    private var contextActivity: Context = context
    private var repositoriesMutableList : MutableList<Repository> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return RepositoriesViewHolder(layoutInflater.inflate(R.layout.content_main_card_view, parent, false))
    }

    override fun getItemCount(): Int {
        return repositoriesMutableList.size
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bindModel(repositoriesMutableList[position])
    }

    fun setRepositories(data: List<Repository>){
        //repositoriesMutableList.clear()
        repositoriesMutableList.addAll(data)
    }

    fun clearRepositories(){
        repositoriesMutableList.clear()
    }


    inner class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rowView: CardView = itemView.findViewById(R.id.linearCardView)
        private val repositoryName: TextView = itemView.findViewById(R.id.repositoryName)
        private val repositoryDescription: TextView = itemView.findViewById(R.id.repositoryDescription)
        private val repositoryStarsNumber: TextView = itemView.findViewById(R.id.repositoryStarsNumber)
        private val repositoryForksNumber: TextView = itemView.findViewById(R.id.repositoryGitForkNumber)
        private val repositoryAvatarImgOwner: ImageView = itemView.findViewById(R.id.repositoryAvatarImgOwner)
        private val repositoryNameOwner: TextView = itemView.findViewById(R.id.repositoryNameOwner)

        fun bindModel(repository: Repository) {

            try {
                repositoryName.text = repository.name
                repositoryDescription.text = repository.description
                repositoryStarsNumber.text = repository.stargazers_count.toString()
                repositoryForksNumber.text = repository.forks_count.toString()
                Picasso.get().load(repository.owner!!.avatar_url).into(repositoryAvatarImgOwner)
                repositoryNameOwner.text = repository.owner!!.login

                rowView.setOnClickListener {

                    try {

                        // Get the text fragment instance
                        val pullRequestListFragment = PRFragment.newInstance(repository.owner!!.login, repository.name)

                        (contextActivity as MainActivity).supportFragmentManager.beginTransaction()
                                .add(R.id.mainFrameLayout, pullRequestListFragment, "PulLRequestListFragment")
                                .addToBackStack(null).commit()

                    }catch (e : IllegalStateException){

                        Toast.makeText(itemView.context, contextActivity.getString(R.string.generalError), Toast.LENGTH_LONG).show()
                    }

                }

            }catch (e : Exception){throw e}


        }

    }

}