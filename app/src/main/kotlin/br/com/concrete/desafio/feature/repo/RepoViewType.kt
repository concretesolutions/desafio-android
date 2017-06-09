package br.com.concrete.desafio.feature.repo

import br.com.concrete.desafio.R
import br.com.concrete.desafio.adapter.ViewType
import br.com.concrete.desafio.feature.pullrequest.PullRequestListActivity
import br.com.concrete.desafio.util.loadUrl
import br.com.concrete.sdk.model.Repo
import kotlinx.android.synthetic.main.item_repo.view.*

fun repoViewType(): ViewType<Repo> {
    return ViewType<Repo>(R.layout.item_repo).apply {
        bind { _, repo, view ->
            if (repo != null) {
                view.title.text = repo.name.capitalize()
                view.description.text = repo.description?.capitalize()
                view.forks.text = "${repo.forks}"
                view.stars.text = "${repo.stargazersCount}"
                view.avatar.loadUrl(repo.owner.avatarUrl)
                view.userLogin.text = repo.owner.login.capitalize()
            }
        }
        click { _, repo, view ->
            view.context.startActivity(PullRequestListActivity.intent(view.context, repo))
        }
    }
}