package br.com.concrete.desafio.base

import android.content.Context
import android.content.Intent
import br.com.concrete.desafio.data.model.dto.RepoDTO

fun Context.intentPullRequestList(repo: RepoDTO): Intent {
    return Intent().setAction("$packageName.PULL_REQUEST_LIST").putExtra("EXTRA_REPO", repo)
}