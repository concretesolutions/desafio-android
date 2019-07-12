package app.kelvao.javapop.home.repositorieslist

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import app.kelvao.javapop.R

class ProgressbarViewHolder(parent: ViewGroup) :
    ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.progressbar_footer, parent, false)) {

    private val loader by lazy { itemView.findViewById<ProgressBar>(R.id.loader) }
    var visibility: Boolean = false
        set(value) {
            field = value
            loader.visibility = if (value) VISIBLE else GONE
        }

}