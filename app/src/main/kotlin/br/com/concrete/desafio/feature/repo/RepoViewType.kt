package br.com.concrete.desafio.feature.repo

import br.com.concrete.desafio.R
import br.com.concrete.desafio.adapter.ViewType
import br.com.concrete.desafio.feature.pullrequest.PullRequestListActivity
import br.com.concrete.sdk.model.Repo
import kotlinx.android.synthetic.main.item_repo.view.*


fun repoViewType(): ViewType<Repo> {
    return ViewType<Repo>(R.layout.item_repo).apply {
        bind { _, repo, view ->
            view.itemRepoRoot.text = repo?.name
        }
        click { _, repo, view ->
            view.context.startActivity(PullRequestListActivity.intent(view.context, repo))
        }
    }
}