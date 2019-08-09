package sergio.com.br.desafioandroid.listeners

import sergio.com.br.desafioandroid.models.PullRequestModel

interface PullRequestListListener {
    fun onItemCliked(item: PullRequestModel)
}