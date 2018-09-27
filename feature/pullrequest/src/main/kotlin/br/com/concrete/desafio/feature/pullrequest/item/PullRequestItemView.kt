package br.com.concrete.desafio.feature.pullrequest.item

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
import br.com.concrete.desafio.data.model.dto.PullRequestDTO
import br.com.concrete.desafio.feature.pullrequest.R

class PullRequestItemView : RelativeLayout, ViewBinder<PullRequestDTO> {

    private val title: TextView
    private val description: TextView
    private val avatar: ImageView
    private val userLogin: TextView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, R.style.PullRequestItemViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        View.inflate(context, R.layout.item_pull_request, this)
        title = findViewById(R.id.title)
        description = findViewById(R.id.description)
        avatar = findViewById(R.id.avatar)
        userLogin = findViewById(R.id.userLogin)
    }

    override fun bind(model: PullRequestDTO) {
        title.text = model.title.capitalize()
        description.text = model.body?.capitalize() ?: ""
        avatar.loadUrl(model.user.avatarUrl)
        userLogin.text = model.user.login.capitalize()
    }
}