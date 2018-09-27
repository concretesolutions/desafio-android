package br.com.concrete.desafio.feature.repo.item

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.arch.toolkit.recycler.adapter.ViewBinder
import br.com.concrete.desafio.base.extension.loadUrl
import br.com.concrete.desafio.data.model.dto.RepoDTO
import br.com.concrete.desafio.feature.repo.R

class RepoItemView : RelativeLayout, ViewBinder<RepoDTO> {

    private val title: TextView
    private val description: TextView
    private val forks: TextView
    private val stars: TextView
    private val avatar: ImageView
    private val userLogin: TextView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, R.style.RepoItemViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        View.inflate(context, R.layout.item_repo, this)
        title = findViewById(R.id.title)
        description = findViewById(R.id.description)
        forks = findViewById(R.id.forks)
        stars = findViewById(R.id.stars)
        avatar = findViewById(R.id.avatar)
        userLogin = findViewById(R.id.userLogin)
    }

    override fun bind(model: RepoDTO) {
        title.text = model.name.capitalize()
        description.text = model.description?.capitalize()
        forks.text = "${model.forks}"
        stars.text = "${model.stargazersCount}"
        avatar.loadUrl(model.owner.avatarUrl)
        userLogin.text = model.owner.login.capitalize()
    }
}