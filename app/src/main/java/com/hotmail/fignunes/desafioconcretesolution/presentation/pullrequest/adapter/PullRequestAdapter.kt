package com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import com.hotmail.fignunes.desafioconcretesolution.R
import com.hotmail.fignunes.desafioconcretesolution.common.Base64
import com.hotmail.fignunes.desafioconcretesolution.common.extensions.reverseDateToBar
import com.hotmail.fignunes.desafioconcretesolution.model.PullRequest
import com.hotmail.fignunes.desafioconcretesolution.repository.remote.owner.responses.OwnerResponses
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class PullRequestAdapter (
    private val context: Activity,
    private val pullRequests: List<PullRequest>,
    private val clickPullRequest: ClickPullRequest,
    private val setPosition: SetPosition
) :
    RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pullrequest, parent, false)
        val holder = ViewHolder(view)
        view.setTag(holder)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullrequest = pullRequests[position]
        holder.title.text = pullrequest.title
        holder.body.text = pullrequest.body
        holder.login.text = pullrequest.user.login

        if(!pullrequest.created_at.isNullOrBlank()) holder.date.text = pullrequest.created_at.reverseDateToBar()

        holder.progressBar.setVisibility(View.GONE)
        loadImage(pullrequest, holder)

        if(pullrequest.user.name.isNullOrBlank()) loadOwner(pullrequest, holder)
        else holder.surname.text = pullrequest.user.name

        holder.layout.setOnClickListener { clickPullRequest.click(pullrequest) }
        setPosition.set(position)
    }

    private fun loadOwner(item: PullRequest, holder: ViewHolder) {
        try {
            val url = item.user.url

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->
                    val gson = GsonBuilder().create()
                    val response = gson.fromJson(response.toString(), OwnerResponses::class.java)

                    val surname = response.name ?: ""
                    holder.surname.text = surname
                    item.user.name = surname
                },
                Response.ErrorListener { error ->
                    holder.surname.text = "erro fail:" + error.message
                })
            val volley = Volley.newRequestQueue(context.applicationContext)
            volley.add(jsonObjectRequest)

        } catch (e: Exception) {
            holder.surname.text = "erro exception:" + e.message
        }
    }

    private fun loadImage(item: PullRequest, holder: ViewHolder) {
        holder.progressBar.setVisibility(View.VISIBLE)
        Picasso.get().load(item.user.avatar_url)
            .error(R.drawable.ic_avatar)
            .into(holder.image, object : Callback {
                override fun onSuccess() {
                    holder.progressBar.setVisibility(View.GONE)
                }

                override fun onError(e: Exception?) {
                    holder.progressBar.setVisibility(View.GONE)
                }
            })
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    interface ClickPullRequest {
        fun click(pullRequest: PullRequest)
    }

    interface SetPosition {
        fun set(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView
        internal var body: TextView
        internal var login : TextView
        internal var surname : TextView
        internal var date : TextView
        internal var progressBar: ProgressBar
        internal var image: ImageView
        internal var layout: LinearLayout

        init {
            layout = itemView.findViewById(R.id.pullRequestLinearLayout) as LinearLayout
            title = itemView.findViewById(R.id.pullRequestTitle) as TextView
            body = itemView.findViewById(R.id.pullRequestBody) as TextView
            login = itemView.findViewById(R.id.pullRequestLogin) as TextView
            surname = itemView.findViewById(R.id.pullRequestSurname) as TextView
            date = itemView.findViewById(R.id.pullRequestDate) as TextView
            image = itemView.findViewById(R.id.pullRequestImage) as ImageView
            progressBar = itemView.findViewById(R.id.pullRequestProgressBar) as ProgressBar
        }
    }
}