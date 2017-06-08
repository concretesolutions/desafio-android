package br.com.concrete.desafio.feature.repo

import br.com.concrete.desafio.R
import br.com.concrete.desafio.adapter.ViewType
import br.com.concrete.desafio.toast
import br.com.concrete.sdk.model.PullRequest
import kotlinx.android.synthetic.main.item_pull_request.view.*

fun pullRequestViewType(): ViewType<PullRequest> {
    return ViewType<PullRequest>(R.layout.item_pull_request).apply {
        bind { _, pullRequest, view ->
            view.itemPullRequestRoot.text = pullRequest?.title
        }
        click { _, pullRequest, view ->
            view.context.toast(pullRequest.title)
        }
    }
}