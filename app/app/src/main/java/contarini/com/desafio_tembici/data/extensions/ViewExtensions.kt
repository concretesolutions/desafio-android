package contarini.com.desafio_tembici.data.extensions

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun View.setVisible(visible : Boolean, useInvisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        useInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}

fun RecyclerView.setup(adapter: RecyclerView.Adapter<in RecyclerView.ViewHolder>,
                       layoutManager: RecyclerView.LayoutManager?,
                       decoration: RecyclerView.ItemDecoration? = null,
                       hasFixedSize: Boolean = true) {

    this.adapter = adapter
    this.layoutManager = layoutManager
    this.setHasFixedSize(hasFixedSize)
    decoration?.let { this.addItemDecoration(it) }
}
