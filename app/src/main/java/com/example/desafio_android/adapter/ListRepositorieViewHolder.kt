package com.example.desafio_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.R
import com.example.desafio_android.model.RepositorieModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class ListRepositorieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_repositories, parent, false)) {

    private var mTxtNomeRepositorio: TextView? = null
    private var mTxtDescRepositorio: TextView? = null
    private var mTxtNomeSobrenome: TextView? = null
    private var mTxtUsername: TextView? = null
    private var mTxtNumerFork: TextView? = null
    private var mTxtStars: TextView? = null
    private var mImageAvatar: ImageView? = null


    init {
        mTxtNomeRepositorio = itemView.findViewById(R.id.txtNomeRepositorio)
        mTxtDescRepositorio = itemView.findViewById(R.id.txtDescRepositorio)
        mTxtNomeSobrenome = itemView.findViewById(R.id.txtNomeSobrenome)
        mTxtUsername = itemView.findViewById(R.id.txtUsername)
        mTxtNumerFork = itemView.findViewById(R.id.txtNumerFork)
        mTxtStars = itemView.findViewById(R.id.txtStars)
        mImageAvatar = itemView.findViewById(R.id.imageAvatar)
    }

    fun bind(repositorie: RepositorieModel) {
        mTxtNomeRepositorio!!.text = repositorie.name
        mTxtDescRepositorio!!.text = repositorie.description
        mTxtNomeSobrenome!!.text = repositorie.name_autor
        mTxtUsername!!.text = repositorie.login ?: ""
        mTxtNumerFork!!.text = repositorie.forksCount.toString()
        mTxtStars!!.text = repositorie.stargazersCount.toString()
        Picasso
            .get()
            .load(repositorie.avatarUrl)
            .transform(CropCircleTransformation())
            .into(mImageAvatar)
    }

}
