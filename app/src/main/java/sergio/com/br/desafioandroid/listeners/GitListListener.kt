package sergio.com.br.desafioandroid.listeners

import sergio.com.br.desafioandroid.models.GitItemsModel

interface GitListListener {
    fun onPaging()

    fun onItemCliked(item: GitItemsModel)
}