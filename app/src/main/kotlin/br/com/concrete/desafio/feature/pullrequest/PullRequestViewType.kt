package br.com.concrete.desafio.feature.pullrequest

import br.com.concrete.desafio.R
import br.com.concrete.desafio.adapter.ViewType
import br.com.concrete.desafio.extension.loadUrl
import br.com.concrete.desafio.extension.toast
import br.com.concrete.sdk.model.PullRequest
import kotlinx.android.synthetic.main.item_pull_request.view.*

fun pullRequestViewType(): ViewType<PullRequest> {
    return ViewType<PullRequest>(R.layout.item_pull_request).apply {
        bind { _, pullRequest, view ->
            if (pullRequest != null) {
                view.title.text = pullRequest.title.capitalize()
                view.description.text = pullRequest.body.capitalize()
                view.avatar.loadUrl(pullRequest.user.avatarUrl)
                view.userLogin.text = pullRequest.user.login.capitalize()
            }
        }
        click { _, _, view ->
            view.context.toast("¯\\_(ツ)_/¯")
        }
    }
}