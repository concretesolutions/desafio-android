package matheusuehara.github.features.repository

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lv_item_repository.view.repository_description
import kotlinx.android.synthetic.main.lv_item_repository.view.repository_full_name
import kotlinx.android.synthetic.main.lv_item_repository.view.repository_user_image
import kotlinx.android.synthetic.main.lv_item_repository.view.repository_name
import kotlinx.android.synthetic.main.lv_item_repository.view.repository_username
import kotlinx.android.synthetic.main.lv_item_repository.view.fork_value
import kotlinx.android.synthetic.main.lv_item_repository.view.star_value
import matheusuehara.github.R
import matheusuehara.github.data.model.Repository

class RepositoryViewHolder(private val view: View, private val repositoryClickListener: RepositoryClickListener) : RecyclerView.ViewHolder(view) {

    var mRepositoryUserImage: ImageView = view.repository_user_image
    var mRepositoryName: TextView = view.repository_name
    var mRepositoryUserName: TextView = view.repository_username
    var mRepositoryFullName: TextView = view.repository_full_name
    var mRepositoryDescription: TextView = view.repository_description
    var mRepositoryStar: TextView = view.star_value
    var mRepositoryFork: TextView = view.fork_value

    fun bindViewHolder(repository: Repository){
        view.setOnClickListener { repositoryClickListener.onClick(repository) }
        mRepositoryName.text = repository.name
        mRepositoryDescription.text = repository.description
        mRepositoryStar.text = repository.stargazers_count.toString()
        mRepositoryFork.text = repository.forks_count.toString()
        mRepositoryUserName.text = repository.owner.login
        mRepositoryFullName.text = ""
        Picasso.get().load(repository.owner.avatar_url).error(R.mipmap.ic_launcher).into(mRepositoryUserImage)

    }

}