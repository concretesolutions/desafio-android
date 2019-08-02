package matheusuehara.github.features.repository

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Picasso
import matheusuehara.github.R
import matheusuehara.github.data.model.Repository

class RepositoryLoadingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var mShimmerFrameLayout: ShimmerFrameLayout = view.findViewById(R.id.shimmer_view_container)
}